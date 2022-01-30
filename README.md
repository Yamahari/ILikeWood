# ILikeWood-1.18.1

This mod adds wood variants for blocks that should have one but don't.

## How to use the API

The api allows you to add all the blocks and items from my mod to your mod. Here are the steps you have to follow to
archive that or if you rather look at how it's done in code, you can check out one of the following GitHub repos where I
implemented calling the API as a separate plugin mod. **NOTE**: You can also put the plugin code straight into your main
mod project, the API is set up in a way that does not break your mod when my mod is not loaded at runtime.

* https://github.com/Yamahari/ILikeWood-BiomesOPlenty-Plugin
* https://github.com/Yamahari/ILikeWood-OTheBiomesYoullGo-Plugin
* https://github.com/Yamahari/ILikeWood-ImmersiveEngineering-Plugin ( still on 1.16.5 )

1) Set up my mod as a dependency for your project. The quickest way to do that is to add your `build/libs` folder as a
   flat dir like this

```
repositories {
    flatDir {
        dir 'build/libs'
    }
} 
```

download my mod + the api (under additional files for each release) manually at
https://www.curseforge.com/minecraft/mc-mods/i-like-wood and put them into the
`build/libs` folder. Then add them as dependencies like this

```
dependencies {
    compileOnly fg.deobf("build.libs:ilikewood:${version_mc}-${version_ilikewood}:api")
    runtimeOnly fg.deobf("build.libs:ilikewood:${version_mc}-${version_ilikewood}")
}
```

**NOTE**: There are more automated ways of doing this (such as curse gradle plugin)
that I will not go over here.

2) Create a new package and classes for the plugin, I will give an example structure

```
yourname.yourmod.plugin
|
+--- yourname.yourmod.plugin.util
|   |
|   +--- yourname.plugin.util.resources
|       |
|       +--- yourname.plugin.util.resources.WoodenLogResource
|       +--- yourname.plugin.util.resources.WoodenPlanksResource
|       +--- yourname.plugin.util.resources.WoodenSlabResource
|       +--- yourname.plugin.util.resources.WoodenStrippedLogResource
|
+--- yourname.yourmod.plugin.MyModPlugin
+--- yourname.yourmod.plugin.MyModWoodenItemTiers
+--- yourname.yourmod.plugin.MyModWoodenResources
+--- yourname.yourmod.plugin.MyModWoodTypes
```

3) Implement the classes created above as such

```java
class WoodenLogResource implements IWoodenLogResource {
    // ...
}
```

```java
class WoodenPlanksResource implements IWoodenPlanksResource {
    // ...
}
```

```java
class WoodenSlabResource implements IWoodenSlabResource {
    // ...
}
```

```java
class WoodenStrippedLogResource implements IWoodenStrippedLogResource {
    // ...
}
```

These four classes are used to tell my API how to find the respective blocks of your mod and some properties about them,
e.g. if your log is animated or not. The functions with `texture` in their name must return the resource location of the
respective textures. The function `getResource` must return the resource location of the actual block.

```java
class MyModWoodenResources {
    public static final IWoodenLogResource MY_WOOD_TYPE_LOG = new WoodenLogResource(/**/);
    public static final IWoodenPlanksResource MY_WOOD_TYPE_PLANKS = new WoodenPlanksResource(/**/);
    public static final IWoodenSlabResource MY_WOOD_TYPE_SLAB = new WoodenSlabResource(/**/);
    public static final IWoodenStrippedLogResource MY_WOOD_TYPE_STRIPPED_LOG = new WoodenStrippedLogResource(/**/);
}
```

This class is just used as a place to hold your resources.

```java
class MyModWoodTypes {
    public static final int[] MY_WOOD_TYPE_COLORS = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    public static final IWoodType MY_WOOD_TYPE = new DefaultWoodType("my_mod_id", "my_wood_type_name", new IWoodType.Colors(MY_WOOD_TYPE_COLORS));
}
```

This class is just used to hold your wood types. The wood type color array **must** hold 8 colors
(integers, RGBA, alpha is lowest, red highest 8 bits) and should be sorted lowest to highest HSV-Value (might need to
experiment a bit if you have odd colors). These colors are used to auto-generate the textures for the blocks & items. If
your planks are a re-palette of vanilla planks then I would use the 7 shades of color you get from that + 1 extra shade.
I would add a darker one if your color is really light, vice versa for darker ones or one shade in between the largest
gap of HSV-values. The `DefaultWoodType` is a default implementation of `IWoodType` and might not fit your needs. If you
already have a block or item from this mod for your own mod for example, you can implement your own wood type and
exclude that type from the respective `getType` function. **Note**: You have to return it under `getBuiltinType` then
and register an additional block or item resource for that block or item in `IModPlugin.registerWoodenResources`.

```java
class MyModWoodenItemTiers {
    public static final IWoodenItemTier MY_WOOD_TYPE = new DefaultWoodenItemTier(YourModWoodTypes.MY_WOOD_TYPE,
        "my_mod_id",
        "my_wood_type_name",
        () -> Ingredient.of(MyModPlugin.BLOCK_REGISTRY.getObject(MyModWoodTypes.MY_WOOD_TYPE, WoodenBlockType.PANELS)));
}
```

This class is just used to hold your wooden item tiers. There is one item tier for each wood type.
The `DefaultWoodenItemTier` is a default implementation of `IWoodenItemTier` and might not fit your needs.

```java
@ILikeWoodPlugin
public class MyModPlugin implements IModPlugin {
    public static IWoodenObjectRegistry<Block, WoodenBlockType> BLOCK_REGISTRY;
    public static IWoodenObjectRegistry<Item, WoodenItemType> ITEM_REGISTRY;
    public static IWoodenObjectRegistry<Item, WoodenBlockType> BLOCK_ITEM_REGISTRY;
    public static IWoodenTieredItemRegistry TIERED_ITEM_REGISTRY;
    public static IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> ENTITY_TYPES;

    @Override
    public String getModId() {
        return "my_mod_id";
    }

    @Override
    public String getPluginModId() {
        return "my_plugin_mod_id";
    }

    @Override
    public void registerWoodTypes(final IWoodTypeRegistry registry) {
        registry.register(MyModWoodTypes.MY_WOOD_TYPE);
    }

    @Override
    public void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
        registry.register(MyModWoodenItemTiers.MY_WOOD_TYPE);
    }

    @Override
    public void registerWoodenResources(final IWoodenResourceRegistry registry) {
        registry.registerPlanksResource(MyModWoodTypes.MY_WOOD_TYPE, MyModWoodenResources.MY_WOOD_TYPE_PLANKS);
        registry.registerLogResource(MyModWoodTypes.MY_WOOD_TYPE, MyModWoodenResources.MY_WOOD_TYPE_LOG);
        registry.registerStrippedLogResource(MyModWoodTypes.MY_WOOD_TYPE, MyModWoodenResources.MY_WOOD_TYPE_STRIPPED_LOG);
        registry.registerSlabResource(MyModWoodTypes.MY_WOOD_TYPE, MyModWoodenResources.MY_WOOD_TYPE_SLAB);
    }

    @Override
    public void acceptBlockRegistry(final IWoodenObjectRegistry<Block, WoodenBlockType> registry) {
        BLOCK_REGISTRY = registry;
    }

    @Override
    public void acceptItemRegistry(final IWoodenObjectRegistry<Item, WoodenItemType> registry) {
        ITEM_REGISTRY = registry;
    }

    @Override
    public void acceptBlockItemRegistry(final IWoodenObjectRegistry<Item, WoodenBlockType> registry) {
        BLOCK_ITEM_REGISTRY = registry;
    }

    @Override
    public void acceptEntityTypeRegistry(final IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> registry) {
        ENTITY_TYPES = registry;
    }

    @Override
    public void acceptTieredItemRegistry(final IWoodenTieredItemRegistry registry) {
        TIERED_ITEM_REGISTRY = registry;
    }
}
```

This is the main plugin class. Note that the mod id and plugin mod id should match if you chose to implement the plugin
right into your main mod instead of into a separate mod.

4) Create a datagen run for this plugin like

```groovy
dataILW {
    workingDirectory project.file('run')
    property 'forge.logging.markers', 'SCAN,REGISTRIES,REGISTRYDUMP'
    property 'forge.logging.console.level', 'debug'
    property 'ilikewood.datagen.modid', 'my_mod_id'
    
    args '--mod', 'ilikewood',
            '--all',
            '--output', file('src/data/resources/ilikewood_resources'),
            '--existing', file('src/main/resources/'),
            '--existing-mod', 'ilikewood',
            '--existing-mod', 'my_mod_id'

    mods {
        my_mod_id {
            source sourceSets.main
        }
    }
}
```

5) Run this datagen run and cross your fingers that everything worked!
6) Include the generated assets & data into your main resource set like

```groovy
sourceSets {
    main {
        resources {
            srcDirs = ['src/main/resources', 'src/data/resources']
        }
    }
}
```

7) Done! When you try to run your mod now, every block&items that you registered from `I Like Wood`
   should be present.
8) If you have any questions come and join my discord https://discord.gg/qn9Aa9u !

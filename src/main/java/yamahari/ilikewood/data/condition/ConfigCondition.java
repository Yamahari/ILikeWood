package yamahari.ilikewood.data.condition;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.util.Constants;

public record ConfigCondition(String config) implements ICondition
{
    private static final ResourceLocation ID = new ResourceLocation(Constants.MOD_ID, "config");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(final IContext context) {
        return ILikeWoodConfig.NAME_TO_CONFIG.get(this.config).isEnabled();
    }

    public static final class ConfigConditionSerializer implements IConditionSerializer<ConfigCondition>
    {
        public static final ConfigConditionSerializer INSTANCE = new ConfigConditionSerializer();

        @Override
        public void write(final JsonObject json, final ConfigCondition value) {
            json.addProperty("config", value.config);
        }

        @Override
        public ConfigCondition read(final JsonObject json) {
            return new ConfigCondition(GsonHelper.getAsString(json, "config"));
        }

        @Override
        public ResourceLocation getID() {
            return ConfigCondition.ID;
        }
    }
}

package com.AxeApps.TabExpand.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.AxeApps.TabExpand.Config;

@Pseudo
@Mixin(targets = "org.bukkit.craftbukkit.v1_7_R4.scoreboard.CraftTeam")
public abstract class MixinCraftTeam {

    @Shadow
    private net.minecraft.scoreboard.ScorePlayerTeam team;

    @Inject(method = "setPrefix", at = @At("HEAD"), cancellable = true)
    private void setPrefixCustom(String prefix, CallbackInfo ci) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        }

        if (!Config.enableExpansion) {
            this.team.setNamePrefix(prefix);
            ci.cancel();
            return;
        }

        int limit = Config.prefixSuffixWriteLimit;

        if (prefix.length() > limit) {
            prefix = prefix.substring(0, limit);
        }

        this.team.setNamePrefix(prefix);
        ci.cancel();
    }

    @Inject(method = "setSuffix", at = @At("HEAD"), cancellable = true)
    private void setSuffixCustom(String suffix, CallbackInfo ci) {
        if (suffix == null) {
            throw new IllegalArgumentException("Suffix cannot be null");
        }

        if (!Config.enableExpansion) {
            this.team.setNameSuffix(suffix);
            ci.cancel();
            return;
        }

        int limit = Config.prefixSuffixWriteLimit;

        if (suffix.length() > limit) {
            suffix = suffix.substring(0, limit);
        }

        this.team.setNameSuffix(suffix);
        ci.cancel();
    }
}

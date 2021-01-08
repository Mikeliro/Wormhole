package com.supermartijn642.wormhole.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Supplier;

/**
 * Created 10/29/2020 by SuperMartijn642
 */
public class WormholeLabel extends Widget {

    private final Supplier<String> text;
    private final boolean translate;

    public WormholeLabel(int x, int y, int width, int height, String title, Supplier<String> text, boolean translate){
        super(x, y, width, height, new TranslationTextComponent(title).getFormattedText());
        this.text = text;
        this.translate = translate;
    }

    public WormholeLabel(int x, int y, int width, int height, String title, String text, boolean translate){
        this(x, y, width, height, title, () -> text, translate);
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks){
        if(this.visible){
            fill(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
            fill(this.x, this.y, this.x + this.width, this.y + this.height, 0xff404040);

            int enabledTextColor = 14737632;
//            int disabledTextColor = 7368816;
            String text = this.text.get();
            FontRenderer font = Minecraft.getInstance().fontRenderer;
            TextComponent textComponent = this.translate ? new TranslationTextComponent(text) : new StringTextComponent(text);
            String s = textComponent.getFormattedText();
            int width = font.getStringWidth(s);
            font.drawString(s, this.x + (this.width - width) / 2f, this.y + 2, enabledTextColor);
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY){
        return this.visible && mouseX >= (double)this.x && mouseX < (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY < (double)(this.y + this.height);
    }
}
package dev.toastersrpg.texture;

import net.kyori.adventure.key.Key;

public enum Textures {
    NEG_1("\uF000", Key.key("toast:general")),
    NEG_2("\uF001", Key.key("toast:general")),
    NEG_3("\uF002", Key.key("toast:general")),
    NEG_4("\uF003", Key.key("toast:general")),
    NEG_5("\uF004", Key.key("toast:general")),
    NEG_6("\uF005", Key.key("toast:general")),
    NEG_7("\uF006", Key.key("toast:general")),
    NEG_8("\uF007", Key.key("toast:general")),
    NEG_9("\uF008", Key.key("toast:general")),
    NEG_10("\uF009", Key.key("toast:general")),
    NEG_11("\uF00A", Key.key("toast:general")),
    NEG_12("\uF00B", Key.key("toast:general")),
    NEG_13("\uF00C", Key.key("toast:general")),
    NEG_14("\uF00D", Key.key("toast:general")),
    NEG_15("\uF00E", Key.key("toast:general")),
    NEG_16("\uF00F", Key.key("toast:general")),
    NEG_17("\uF010", Key.key("toast:general")),
    NEG_18("\uF011", Key.key("toast:general")),
    NEG_19("\uF012", Key.key("toast:general")),
    NEG_20("\uF013", Key.key("toast:general")),

    POS_1("\uF014", Key.key("toast:general")),
    POS_2("\uF015", Key.key("toast:general")),
    POS_3("\uF016", Key.key("toast:general")),
    POS_4("\uF017", Key.key("toast:general")),
    POS_5("\uF018", Key.key("toast:general")),
    POS_6("\uF019", Key.key("toast:general")),
    POS_7("\uF01A", Key.key("toast:general")),
    POS_8("\uF01B", Key.key("toast:general")),
    POS_9("\uF01C", Key.key("toast:general")),
    POS_10("\uF01D", Key.key("toast:general")),
    POS_11("\uF01E", Key.key("toast:general")),
    POS_12("\uF01F", Key.key("toast:general")),
    POS_13("\uF020", Key.key("toast:general")),
    POS_14("\uF021", Key.key("toast:general")),
    POS_15("\uF022", Key.key("toast:general")),
    POS_16("\uF023", Key.key("toast:general")),
    POS_17("\uF024", Key.key("toast:general")),
    POS_18("\uF025", Key.key("toast:general")),
    POS_19("\uF026", Key.key("toast:general")),
    POS_20("\uF027", Key.key("toast:general")),

    HEALTH_BAR_BACKGROUND("\uE000", Key.key("toast:general")),
    HEALTH_BAR_PROGRESS("\uE001", Key.key("toast:general"));

    public final String code;
    public final Key key;

    Textures(String code, Key key) {
        this.code = code;
        this.key = key;
    }

    public static final Key generalKey = Key.key("toast:general");
}


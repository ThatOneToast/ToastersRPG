package dev.toastersrpg.texture;

import net.kyori.adventure.key.Key;

public class Textures {
    //Usage Example >> Component.text(Codes.NEG1.getCode()).font(spacesKey);
    public static final Key spacesKey = Key.key("toast:spaces");
    public static final Key hudKey = Key.key("toast:hud");


    public enum Codes {
        NEG_1("\uF000", Key.key("toast:spaces")),
        NEG_2("\uF001", Key.key("toast:spaces")),
        NEG_3("\uF002", Key.key("toast:spaces")),
        NEG_4("\uF003", Key.key("toast:spaces")),
        NEG_5("\uF004", Key.key("toast:spaces")),
        NEG_6("\uF005", Key.key("toast:spaces")),
        NEG_7("\uF006", Key.key("toast:spaces")),
        NEG_8("\uF007", Key.key("toast:spaces")),
        NEG_9("\uF008", Key.key("toast:spaces")),
        NEG_10("\uF009", Key.key("toast:spaces")),
        NEG_11("\uF00A", Key.key("toast:spaces")),
        NEG_12("\uF00B", Key.key("toast:spaces")),
        NEG_13("\uF00C", Key.key("toast:spaces")),
        NEG_14("\uF00D", Key.key("toast:spaces")),
        NEG_15("\uF00E", Key.key("toast:spaces")),
        NEG_16("\uF00F", Key.key("toast:spaces")),
        NEG_17("\uF010", Key.key("toast:spaces")),
        NEG_18("\uF011", Key.key("toast:spaces")),
        NEG_19("\uF012", Key.key("toast:spaces")),
        NEG_20("\uF013", Key.key("toast:spaces")),

        POS_1("\uF014", Key.key("toast:spaces")),
        POS_2("\uF015", Key.key("toast:spaces")),
        POS_3("\uF016", Key.key("toast:spaces")),
        POS_4("\uF017", Key.key("toast:spaces")),
        POS_5("\uF018", Key.key("toast:spaces")),
        POS_6("\uF019", Key.key("toast:spaces")),
        POS_7("\uF01A", Key.key("toast:spaces")),
        POS_8("\uF01B", Key.key("toast:spaces")),
        POS_9("\uF01C", Key.key("toast:spaces")),
        POS_10("\uF01D", Key.key("toast:spaces")),
        POS_11("\uF01E", Key.key("toast:spaces")),
        POS_12("\uF01F", Key.key("toast:spaces")),
        POS_13("\uF020", Key.key("toast:spaces")),
        POS_14("\uF021", Key.key("toast:spaces")),
        POS_15("\uF022", Key.key("toast:spaces")),
        POS_16("\uF023", Key.key("toast:spaces")),
        POS_17("\uF024", Key.key("toast:spaces")),
        POS_18("\uF025", Key.key("toast:spaces")),
        POS_19("\uF026", Key.key("toast:spaces")),
        POS_20("\uF027", Key.key("toast:spaces")),

        HEALTH_BAR_BACKGROUND("\uE000", Key.key("toast:hud")),
        HEALTH_BAR_PROGRESS("\uE001", Key.key("toast:hud"));

        private final String code;
        private final Key key;

        Codes(String code, Key key) {
            this.code = code;
            this.key = key;
        }

        public String getCode() {
            return code;
        }

        public Key getKey() {
            return key;
        }
    }
}


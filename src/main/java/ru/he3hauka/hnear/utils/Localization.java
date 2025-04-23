package ru.he3hauka.hnear.utils;

import java.util.HashMap;
import java.util.Map;

public class Localization {

    private final String locale;

    public Localization(String locale) {
        this.locale = locale.toLowerCase();
    }

    public String get(String key, Object... args) {
        String message = MESSAGES.getOrDefault(locale, MESSAGES.get("en")).getOrDefault(key, "§cMissing translation: " + key);
        for (int i = 0; i < args.length; i++) {
            message = message.replace("{" + i + "}", args[i].toString());
        }
        return message;
    }

    private static final Map<String, Map<String, String>> MESSAGES = new HashMap<>();

    static {
        Map<String, String> en = new HashMap<>();
        en.put("no_permission", "§cYou don't have permission!");
        en.put("reloading_plugin", "Reloading plugin...");
        en.put("plugin_reloaded", "Plugin reloaded in §a{0}§f ms");
        MESSAGES.put("en", en);

        Map<String, String> ru = new HashMap<>();
        ru.put("no_permission", "§cУ вас нет прав!");
        ru.put("reloading_plugin", "Перезагрузка плагина...");
        ru.put("plugin_reloaded", "Плагин перезагружен за §a{0}§f мс");
        MESSAGES.put("ru", ru);

        Map<String, String> uk = new HashMap<>();
        uk.put("no_permission", "§cУ вас немає прав!");
        uk.put("reloading_plugin", "Перезавантаження плагіна...");
        uk.put("plugin_reloaded", "Плагін перезавантажено за §a{0}§f мс");
        MESSAGES.put("uk", uk);

        Map<String, String> fr = new HashMap<>();
        fr.put("no_permission", "§cVous n'avez pas la permission !");
        fr.put("reloading_plugin", "Rechargement du plugin...");
        fr.put("plugin_reloaded", "Plugin rechargé en §a{0}§f ms");
        MESSAGES.put("fr", fr);

        Map<String, String> de = new HashMap<>();
        de.put("no_permission", "§cSie haben keine Berechtigung!");
        de.put("reloading_plugin", "Plugin wird neu geladen...");
        de.put("plugin_reloaded", "Plugin neu geladen in §a{0}§f ms");
        MESSAGES.put("de", de);

        Map<String, String> es = new HashMap<>();
        es.put("no_permission", "§c¡No tienes permiso!");
        es.put("reloading_plugin", "Recargando el plugin...");
        es.put("plugin_reloaded", "Plugin recargado en §a{0}§f ms");
        MESSAGES.put("es", es);

        Map<String, String> zh = new HashMap<>();
        zh.put("no_permission", "§c你没有权限!");
        zh.put("reloading_plugin", "正在重新加载插件...");
        zh.put("plugin_reloaded", "插件已重新加载，用时 §a{0}§f 毫秒");
        MESSAGES.put("zh", zh);

        Map<String, String> ar = new HashMap<>();
        ar.put("no_permission", "§cليس لديك إذن!");
        ar.put("reloading_plugin", "جارٍ إعادة تحميل الإضافة...");
        ar.put("plugin_reloaded", "تم إعادة تحميل الإضافة في §a{0}§f مللي ثانية");
        MESSAGES.put("ar", ar);

        Map<String, String> pl = new HashMap<>();
        pl.put("no_permission", "§cNie masz uprawnień!");
        pl.put("reloading_plugin", "Przeładowywanie wtyczki...");
        pl.put("plugin_reloaded", "Wtyczka przeładowana w §a{0}§f ms");
        MESSAGES.put("pl", pl);

        Map<String, String> pt = new HashMap<>();
        pt.put("no_permission", "§cVocê não tem permissão!");
        pt.put("reloading_plugin", "Recarregando o plugin...");
        pt.put("plugin_reloaded", "Plugin recarregado em §a{0}§f ms");
        MESSAGES.put("pt", pt);

        Map<String, String> it = new HashMap<>();
        it.put("no_permission", "§cNon hai il permesso!");
        it.put("reloading_plugin", "Ricaricamento del plugin...");
        it.put("plugin_reloaded", "Plugin ricaricato in §a{0}§f ms");
        MESSAGES.put("it", it);

        Map<String, String> be = new HashMap<>();
        be.put("no_permission", "§cУ вас няма дазволу!");
        be.put("reloading_plugin", "Перазагрузка плагіна...");
        be.put("plugin_reloaded", "Плагін перазагружаны за §a{0}§f мс");
        MESSAGES.put("be", be);

        Map<String, String> af = new HashMap<>();
        af.put("no_permission", "§cJy het nie toestemming nie!");
        af.put("reloading_plugin", "Herlaai van plugin...");
        af.put("plugin_reloaded", "Plugin herlaai in §a{0}§f ms");
        MESSAGES.put("af", af);

        Map<String, String> sq = new HashMap<>();
        sq.put("no_permission", "§cNuk keni leje!");
        sq.put("reloading_plugin", "Rikthim i plugin...");
        sq.put("plugin_reloaded", "Plugin u ngarkua ne §a{0}§f ms");
        MESSAGES.put("sq", sq);

        Map<String, String> am = new HashMap<>();
        am.put("no_permission", "§cእርስዎ ፈቃድ የለዎትም!");
        am.put("reloading_plugin", "ፕላግን እንደገና ማስገባት...");
        am.put("plugin_reloaded", "ፕላግን በ§a{0}§f ሚሴኮንድ ተመለሰ");
        MESSAGES.put("am", am);

        Map<String, String> hy = new HashMap<>();
        hy.put("no_permission", "§cԴուք թույլտվություն չունեք!");
        hy.put("reloading_plugin", "Պլագինի վերալիցքավորում...");
        hy.put("plugin_reloaded", "Պլագինը վերալիցքավորվեց §a{0}§f միլիվայրկյանում");
        MESSAGES.put("hy", hy);

        Map<String, String> bn = new HashMap<>();
        bn.put("no_permission", "§cআপনার অনুমতি নেই!");
        bn.put("reloading_plugin", "প্লাগইন রিলোড হচ্ছে...");
        bn.put("plugin_reloaded", "প্লাগইন §a{0}§f মিলিসেকেন্ডে রিলোড হয়েছে");
        MESSAGES.put("bn", bn);

        Map<String, String> bs = new HashMap<>();
        bs.put("no_permission", "§cNemate dozvolu!");
        bs.put("reloading_plugin", "Ponovno učitavanje plugina...");
        bs.put("plugin_reloaded", "Plugin je ponovno učitan za §a{0}§f ms");
        MESSAGES.put("bs", bs);

        Map<String, String> ca = new HashMap<>();
        ca.put("no_permission", "§cNo tens permís!");
        ca.put("reloading_plugin", "Recarregant el plugin...");
        ca.put("plugin_reloaded", "Plugin recarregat en §a{0}§f ms");
        MESSAGES.put("ca", ca);

        Map<String, String> hr = new HashMap<>();
        hr.put("no_permission", "§cNemate dopuštenje!");
        hr.put("reloading_plugin", "Ponovno učitavanje dodatka...");
        hr.put("plugin_reloaded", "Dodatak ponovno učitan za §a{0}§f ms");
        MESSAGES.put("hr", hr);

        Map<String, String> ro = new HashMap<>();
        ro.put("no_permission", "§cNu aveți permisiunea!");
        ro.put("reloading_plugin", "Se reîncarcă pluginul...");
        ro.put("plugin_reloaded", "Plugin reîncărcat în §a{0}§f ms");
        MESSAGES.put("ro", ro);

        Map<String, String> la = new HashMap<>();
        la.put("no_permission", "§cNon habes permissionem!");
        la.put("reloading_plugin", "Plugin recondito...");
        la.put("plugin_reloaded", "Plugin recondito in §a{0}§f ms");
        MESSAGES.put("la", la);
    }
}

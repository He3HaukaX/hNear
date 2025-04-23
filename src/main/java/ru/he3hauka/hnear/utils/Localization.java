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
        en.put("update_available", "§eNew version §a{0} §eis available! Current version: §c{1}");
        en.put("download_here", "§eDownload: §b{0}");
        en.put("up_to_date", "§aPlugin is up to date! Version: §e{0}");
        en.put("update_check_failed_code", "§cFailed to check for updates. HTTP code: {0}");
        en.put("update_check_failed_error", "§cFailed to check for updates: {0}");
        MESSAGES.put("en", en);

        Map<String, String> ru = new HashMap<>();
        ru.put("no_permission", "§cУ вас нет прав!");
        ru.put("reloading_plugin", "Перезагрузка плагина...");
        ru.put("plugin_reloaded", "Плагин перезагружен за §a{0}§f мс");
        ru.put("update_available", "§eДоступна новая версия §a{0}§e! Текущая версия: §c{1}");
        ru.put("download_here", "§eСкачать: §b{0}");
        ru.put("up_to_date", "§aПлагин обновлён! Версия: §e{0}");
        ru.put("update_check_failed_code", "§cОшибка проверки обновлений. Код HTTP: {0}");
        ru.put("update_check_failed_error", "§cОшибка проверки обновлений: {0}");
        MESSAGES.put("ru", ru);

        Map<String, String> uk = new HashMap<>();
        uk.put("no_permission", "§cУ вас немає прав!");
        uk.put("reloading_plugin", "Перезавантаження плагіна...");
        uk.put("plugin_reloaded", "Плагін перезавантажено за §a{0}§f мс");
        uk.put("update_available", "§eДоступна нова версія §a{0}§e! Поточна версія: §c{1}");
        uk.put("download_here", "§eЗавантажити: §b{0}");
        uk.put("up_to_date", "§aПлагін оновлено! Версія: §e{0}");
        uk.put("update_check_failed_code", "§cПомилка перевірки оновлень. Код HTTP: {0}");
        uk.put("update_check_failed_error", "§cПомилка перевірки оновлень: {0}");
        MESSAGES.put("uk", uk);

        Map<String, String> fr = new HashMap<>();
        fr.put("no_permission", "§cVous n'avez pas la permission !");
        fr.put("reloading_plugin", "Rechargement du plugin...");
        fr.put("plugin_reloaded", "Plugin rechargé en §a{0}§f ms");
        fr.put("update_available", "§eNouvelle version §a{0} §edisponible ! Version actuelle : §c{1}");
        fr.put("download_here", "§eTélécharger : §b{0}");
        fr.put("up_to_date", "§aPlugin à jour ! Version : §e{0}");
        fr.put("update_check_failed_code", "§cÉchec de la vérification des mises à jour. Code HTTP : {0}");
        fr.put("update_check_failed_error", "§cÉchec de la vérification des mises à jour : {0}");
        MESSAGES.put("fr", fr);

        Map<String, String> de = new HashMap<>();
        de.put("no_permission", "§cSie haben keine Berechtigung!");
        de.put("reloading_plugin", "Plugin wird neu geladen...");
        de.put("plugin_reloaded", "Plugin neu geladen in §a{0}§f ms");
        de.put("update_available", "§eNeue Version §a{0} §everfügbar! Aktuelle Version: §c{1}");
        de.put("download_here", "§eDownload: §b{0}");
        de.put("up_to_date", "§aPlugin ist auf dem neuesten Stand! Version: §e{0}");
        de.put("update_check_failed_code", "§cUpdate-Prüfung fehlgeschlagen. HTTP-Code: {0}");
        de.put("update_check_failed_error", "§cUpdate-Prüfung fehlgeschlagen: {0}");
        MESSAGES.put("de", de);

        Map<String, String> es = new HashMap<>();
        es.put("no_permission", "§c¡No tienes permiso!");
        es.put("reloading_plugin", "Recargando el plugin...");
        es.put("plugin_reloaded", "Plugin recargado en §a{0}§f ms");
        es.put("update_available", "§e¡Nueva versión §a{0} §edisponible! Versión actual: §c{1}");
        es.put("download_here", "§eDescargar: §b{0}");
        es.put("up_to_date", "§a¡El plugin está actualizado! Versión: §e{0}");
        es.put("update_check_failed_code", "§cError al comprobar actualizaciones. Código HTTP: {0}");
        es.put("update_check_failed_error", "§cError al comprobar actualizaciones: {0}");
        MESSAGES.put("es", es);

        Map<String, String> zh = new HashMap<>();
        zh.put("no_permission", "§c你没有权限!");
        zh.put("reloading_plugin", "正在重新加载插件...");
        zh.put("plugin_reloaded", "插件已重新加载，用时 §a{0}§f 毫秒");
        zh.put("update_available", "§e新版本 §a{0} §e可用！当前版本：§c{1}");
        zh.put("download_here", "§e下载：§b{0}");
        zh.put("up_to_date", "§a插件已是最新版本！版本：§e{0}");
        zh.put("update_check_failed_code", "§c检查更新失败。HTTP 代码：{0}");
        zh.put("update_check_failed_error", "§c检查更新失败：{0}");
        MESSAGES.put("zh", zh);

        Map<String, String> ar = new HashMap<>();
        ar.put("no_permission", "§cليس لديك إذن!");
        ar.put("reloading_plugin", "جارٍ إعادة تحميل الإضافة...");
        ar.put("plugin_reloaded", "تم إعادة تحميل الإضافة في §a{0}§f مللي ثانية");
        ar.put("update_available", "§eالإصدار الجديد §a{0} §eمتاح! الإصدار الحالي: §c{1}");
        ar.put("download_here", "§eتحميل: §b{0}");
        ar.put("up_to_date", "§aالإضافة محدثة! الإصدار: §e{0}");
        ar.put("update_check_failed_code", "§cفشل في التحقق من التحديثات. رمز HTTP: {0}");
        ar.put("update_check_failed_error", "§cفشل في التحقق من التحديثات: {0}");
        MESSAGES.put("ar", ar);

        Map<String, String> pl = new HashMap<>();
        pl.put("no_permission", "§cNie masz uprawnień!");
        pl.put("reloading_plugin", "Przeładowywanie wtyczki...");
        pl.put("plugin_reloaded", "Wtyczka przeładowana w §a{0}§f ms");
        pl.put("update_available", "§eNowa wersja §a{0} §ejest dostępna! Obecna wersja: §c{1}");
        pl.put("download_here", "§ePobierz: §b{0}");
        pl.put("up_to_date", "§aWtyczka jest aktualna! Wersja: §e{0}");
        pl.put("update_check_failed_code", "§cNie udało się sprawdzić aktualizacji. Kod HTTP: {0}");
        pl.put("update_check_failed_error", "§cNie udało się sprawdzić aktualizacji: {0}");
        MESSAGES.put("pl", pl);

        Map<String, String> pt = new HashMap<>();
        pt.put("no_permission", "§cVocê não tem permissão!");
        pt.put("reloading_plugin", "Recarregando o plugin...");
        pt.put("plugin_reloaded", "Plugin recarregado em §a{0}§f ms");
        pt.put("update_available", "§eNova versão §a{0} §edisponível! Versão atual: §c{1}");
        pt.put("download_here", "§eDownload: §b{0}");
        pt.put("up_to_date", "§aO plugin está atualizado! Versão: §e{0}");
        pt.put("update_check_failed_code", "§cFalha ao verificar atualizações. Código HTTP: {0}");
        pt.put("update_check_failed_error", "§cFalha ao verificar atualizações: {0}");
        MESSAGES.put("pt", pt);

        Map<String, String> it = new HashMap<>();
        it.put("no_permission", "§cNon hai il permesso!");
        it.put("reloading_plugin", "Ricaricamento del plugin...");
        it.put("plugin_reloaded", "Plugin ricaricato in §a{0}§f ms");
        it.put("update_available", "§eNuova versione §a{0} §edisponibile! Versione attuale: §c{1}");
        it.put("download_here", "§eScarica: §b{0}");
        it.put("up_to_date", "§aIl plugin è aggiornato! Versione: §e{0}");
        it.put("update_check_failed_code", "§cImpossibile verificare gli aggiornamenti. Codice HTTP: {0}");
        it.put("update_check_failed_error", "§cImpossibile verificare gli aggiornamenti: {0}");
        MESSAGES.put("it", it);

        Map<String, String> be = new HashMap<>();
        be.put("no_permission", "§cУ вас няма дазволу!");
        be.put("reloading_plugin", "Перазагрузка плагіна...");
        be.put("plugin_reloaded", "Плагін перазагружаны за §a{0}§f мс");
        be.put("update_available", "§eДаступная новая версія §a{0}§e! Бягучая версія: §c{1}");
        be.put("download_here", "§eСпампаваць: §b{0}");
        be.put("up_to_date", "§aПлагін абноўлены! Версія: §e{0}");
        be.put("update_check_failed_code", "§cПамылка праверкі абнаўленняў. Код HTTP: {0}");
        be.put("update_check_failed_error", "§cПамылка праверкі абнаўленняў: {0}");
        MESSAGES.put("be", be);

        Map<String, String> af = new HashMap<>();
        af.put("no_permission", "§cJy het nie toestemming nie!");
        af.put("reloading_plugin", "Herlaai van plugin...");
        af.put("plugin_reloaded", "Plugin herlaai in §a{0}§f ms");
        af.put("update_available", "§eNuwe weergawe §a{0} §ebeskikbaar! Huidige weergawe: §c{1}");
        af.put("download_here", "§eAflaai: §b{0}");
        af.put("up_to_date", "§aDie plugin is op datum! Weergawe: §e{0}");
        af.put("update_check_failed_code", "§cKon nie updates nagaan nie. HTTP-kode: {0}");
        af.put("update_check_failed_error", "§cKon nie updates nagaan nie: {0}");
        MESSAGES.put("af", af);

        Map<String, String> sq = new HashMap<>();
        sq.put("no_permission", "§cNuk keni leje!");
        sq.put("reloading_plugin", "Rikthim i plugin...");
        sq.put("plugin_reloaded", "Plugin u ngarkua ne §a{0}§f ms");
        sq.put("update_available", "§eVersion i ri §a{0} §eështë i disponueshëm! Version aktual: §c{1}");
        sq.put("download_here", "§eShkarko: §b{0}");
        sq.put("up_to_date", "§aPlugin është i përditësuar! Version: §e{0}");
        sq.put("update_check_failed_code", "§cDështoi të kontrollonte për përditësime. Kod HTTP: {0}");
        sq.put("update_check_failed_error", "§cDështoi të kontrollonte për përditësime: {0}");
        MESSAGES.put("sq", sq);

        Map<String, String> am = new HashMap<>();
        am.put("no_permission", "§cእርስዎ ፈቃድ የለዎትም!");
        am.put("reloading_plugin", "ፕላግን እንደገና ማስገባት...");
        am.put("plugin_reloaded", "ፕላግን በ§a{0}§f ሚሴኮንድ ተመለሰ");
        am.put("update_available", "§eአዲስ ስሪት §a{0} §eአሁን ይገኛል! የአሁኑ ስሪት: §c{1}");
        am.put("download_here", "§eከዚህ ያውርዱ: §b{0}");
        am.put("up_to_date", "§aፕላጊኑ ዘምኗል! ስሪት: §e{0}");
        am.put("update_check_failed_code", "§cለዝመናዎች ሲፈትሽ ውድቅ ሆኗል። የHTTP ኮድ: {0}");
        am.put("update_check_failed_error", "§cለዝመናዎች ሲፈትሽ ውድቅ ሆኗል: {0}");
        MESSAGES.put("am", am);

        Map<String, String> hy = new HashMap<>();
        hy.put("no_permission", "§cԴուք թույլտվություն չունեք!");
        hy.put("reloading_plugin", "Պլագինի վերալիցքավորում...");
        hy.put("plugin_reloaded", "Պլագինը վերալիցքավորվեց §a{0}§f միլիվայրկյանում");
        hy.put("update_available", "§eՆոր տարբերակ §a{0} §eհասանելի է! Ընթացիկ տարբերակ: §c{1}");
        hy.put("download_here", "§eՆերբեռնել: §b{0}");
        hy.put("up_to_date", "§aՊլագինը թարմացված է! Տարբերակ: §e{0}");
        hy.put("update_check_failed_code", "§cԹարմացումների ստուգումը ձախողվեց: HTTP կոդ: {0}");
        hy.put("update_check_failed_error", "§cԹարմացումների ստուգումը ձախողվեց: {0}");
        MESSAGES.put("hy", hy);

        Map<String, String> bn = new HashMap<>();
        bn.put("no_permission", "§cআপনার অনুমতি নেই!");
        bn.put("reloading_plugin", "প্লাগইন রিলোড হচ্ছে...");
        bn.put("plugin_reloaded", "প্লাগইন §a{0}§f মিলিসেকেন্ডে রিলোড হয়েছে");
        bn.put("update_available", "§eনতুন সংস্করণ §a{0} §eউপলব্ধ! বর্তমান সংস্করণ: §c{1}");
        bn.put("download_here", "§eডাউনলোড করুন: §b{0}");
        bn.put("up_to_date", "§aপ্লাগইন আপডেট করা হয়েছে! সংস্করণ: §e{0}");
        bn.put("update_check_failed_code", "§cআপডেট পরীক্ষা ব্যর্থ হয়েছে। HTTP কোড: {0}");
        bn.put("update_check_failed_error", "§cআপডেট পরীক্ষা ব্যর্থ হয়েছে: {0}");
        MESSAGES.put("bn", bn);

        Map<String, String> bs = new HashMap<>();
        bs.put("no_permission", "§cNemate dozvolu!");
        bs.put("reloading_plugin", "Ponovno učitavanje plugina...");
        bs.put("plugin_reloaded", "Plugin je ponovno učitan za §a{0}§f ms");
        bs.put("update_available", "§eNova verzija §a{0} §edostupna! Trenutna verzija: §c{1}");
        bs.put("download_here", "§ePreuzmi: §b{0}");
        bs.put("up_to_date", "§aPlugin je ažuriran! Verzija: §e{0}");
        bs.put("update_check_failed_code", "§cNeuspjela provjera ažuriranja. HTTP kod: {0}");
        bs.put("update_check_failed_error", "§cNeuspjela provjera ažuriranja: {0}");
        MESSAGES.put("bs", bs);

        Map<String, String> ca = new HashMap<>();
        ca.put("no_permission", "§cNo tens permís!");
        ca.put("reloading_plugin", "Recarregant el plugin...");
        ca.put("plugin_reloaded", "Plugin recarregat en §a{0}§f ms");
        ca.put("update_available", "§eNova versió §a{0} §edisponible! Versió actual: §c{1}");
        ca.put("download_here", "§eDescarrega: §b{0}");
        ca.put("up_to_date", "§aEl plugin està actualitzat! Versió: §e{0}");
        ca.put("update_check_failed_code", "§cError en comprovar actualitzacions. Codi HTTP: {0}");
        ca.put("update_check_failed_error", "§cError en comprovar actualitzacions: {0}");
        MESSAGES.put("ca", ca);

        Map<String, String> hr = new HashMap<>();
        hr.put("no_permission", "§cNemate dopuštenje!");
        hr.put("reloading_plugin", "Ponovno učitavanje dodatka...");
        hr.put("plugin_reloaded", "Dodatak ponovno učitan za §a{0}§f ms");
        hr.put("update_available", "§eNova verzija §a{0} §edostupna! Trenutna verzija: §c{1}");
        hr.put("download_here", "§ePreuzmi: §b{0}");
        hr.put("up_to_date", "§aDodatak je ažuriran! Verzija: §e{0}");
        hr.put("update_check_failed_code", "§cProvjera ažuriranja nije uspjela. HTTP kod: {0}");
        hr.put("update_check_failed_error", "§cProvjera ažuriranja nije uspjela: {0}");
        MESSAGES.put("hr", hr);

        Map<String, String> ro = new HashMap<>();
        ro.put("no_permission", "§cNu aveți permisiunea!");
        ro.put("reloading_plugin", "Se reîncarcă pluginul...");
        ro.put("plugin_reloaded", "Plugin reîncărcat în §a{0}§f ms");
        ro.put("update_available", "§eVersiune nouă §a{0} §edisponibilă! Versiune curentă: §c{1}");
        ro.put("download_here", "§eDescarcă: §b{0}");
        ro.put("up_to_date", "§aPluginul este la zi! Versiune: §e{0}");
        ro.put("update_check_failed_code", "§cVerificarea actualizărilor a eșuat. Cod HTTP: {0}");
        ro.put("update_check_failed_error", "§cVerificarea actualizărilor a eșuat: {0}");
        MESSAGES.put("ro", ro);

        Map<String, String> la = new HashMap<>();
        la.put("no_permission", "§cNon habes permissionem!");
        la.put("reloading_plugin", "Plugin recondito...");
        la.put("plugin_reloaded", "Plugin recondito in §a{0}§f ms");
        la.put("update_available", "§eNova versio §a{0} §epraesto est! Versio currentis: §c{1}");
        la.put("download_here", "§eDownload: §b{0}");
        la.put("up_to_date", "§aPlugin est currentis! Versio: §e{0}");
        la.put("update_check_failed_code", "§cUpdate check defecit. HTTP codex: {0}");
        la.put("update_check_failed_error", "§cUpdate check defecit: {0}");
        MESSAGES.put("la", la);
    }
}
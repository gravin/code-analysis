package com.codeanalysis.csdn;

import com.codeanalysis.DocumentUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Gavin
 * @date 2020/7/13
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Collection<File> htmls = FileUtils.listFiles(new File("C:\\Users\\Gravin\\Desktop\\to print"), new String[]{"html"}
                , false);
        Optional<File> aHtml = htmls.stream().sorted(Comparator.comparing((File file) -> file.lastModified()).reversed()).findFirst();
        File html = aHtml.get();

        Document document = DocumentUtil.getDocument(html, new HashMap<>());
        document.outputSettings().prettyPrint(false);

        /**
         *
         *
         *  直接用下面吧
         *
         * (function(){
         * $("#side").remove();
         * $("#comment_title, #comment_list, #comment_bar, #comment_form, .announce, #ad_cen, #ad_bot").remove();
         * $(".nav_top_2011, #header, #navigator").remove();
         * $(".p4course_target, .comment-box, .recommend-box, #csdn-toolbar, #tool-box").remove();
         * $("aside").remove();
         * $(".tool-box").remove();
         * $("main").css('display','content');
         * $("main").css('float','left');
         * window.print();
         *
         * $("tool-box").remove();
         * })();
         *
         *
         *
         *
         *
         */

        document.getElementsByTag("div").attr("hidden", "true");
        document.getElementsByClass("blog-content-box").removeAttr("hidden");

        document.getElementsByClass("blog-content-box").get(0).getElementsByTag("div").removeAttr("hidden");
        document.getElementsByClass("blog-content-box").get(0).parents().removeAttr("hidden");


        addStyles(document.getElementById("mainBox"), ";width:100%");
        addStyles(document.getElementsByTag("main").get(0), ";display:content;float:left");


//        document.getElementsByTag("main").get(0).attr("style", document.getElementsByTag("main").get(0).attr("style") + ";display:content;float:left;");


        // https://blog.csdn.net/wydbyxr/article/details/84643758
        List<String> classToRemove = Arrays.asList("tool-box", "p4course_target", "comment-box", "recommend-box", "nav_top_2011", "announce");
        List<String> idsToRemove = Arrays.asList("side", "comment_title", "comment_bar", "comment_form", "recommend-box", "ad_cen", "ad_bot", "header", "navigator",
                "csdn-toolbar", "tool-box");
        List<String> tagsToRemove = Arrays.asList("aside", "tool-box", "script");


        for (Element element : document.getElementsByTag("img")) {
            if (element.hasAttr("onerror")) {
                element.remove();
                break;
            }
        }

        for (Element element : document.getAllElements()) {
            if ("true".equals(element.attr("hidden"))) {
                element.remove();
                System.out.println("hidden " + element.id() + " removed");
                continue;
            }
            boolean hasClassToRemove = element.classNames().stream().filter(s -> classToRemove.contains(s)).findAny().isPresent();
            if (hasClassToRemove) {
                element.remove();
                System.out.println(element.classNames() + " removed");
                continue;
            }
            if (idsToRemove.contains(element.id())) {
                element.remove();
                System.out.println(element.id() + " removed");
                continue;
            }
            if (tagsToRemove.contains(element.tagName())) {
                element.remove();
                System.out.println(element.tagName() + " removed");
                continue;
            }
        }

        DocumentUtil.write(html, document);


        System.out.println(html.getName() + " has been modified");
    }

    private static void addStyles(Element element, String styleString) {
        if (StringUtils.isBlank(styleString)) {
            return;
        }
        String[] styles = styleString.split(";");
        String oldStyle = element.attr("style");
        String newStyle = oldStyle;
        for (String style : styles) {
            if (style.contains(":")) {
                String[] split = style.split(":");
                if (StringUtils.isNotBlank(split[0]) && !oldStyle.contains(split[0])) {
                    newStyle = newStyle + ";" + style;
                }
            }
        }
        element.attr("style", newStyle);
    }

    private static void replaceHtml(File html) throws IOException {
        String content = FileUtils.readFileToString(html, "UTF-8");
//        content = content.replace("id=\"header\"", "id=\"header\" hidden")
//                .replace("id=\"bread_crumb\"", "id=\"bread_crumb\" hidden")
//                .replace("id=\"bread_crumb\"", "id=\"bread_crumb\" hidden")
//                .replace("class=\"meta\"", "class=\"meta\" hidden")
//                .replace("id=\"comments_wrapper\"", "id=\"comments_wrapper\" hidden")
//                .replace("id=\"previous_next_post_single\"", "id=\"previous_next_post_single\" hidden")
//                .replace("id=\"container\"", "id=\"container\" hidden")
//                .replace("id=\"right_col\"", "id=\"right_col\" hidden")
//                .replace("id=\"footer\"", "id=\"footer\" hidden")
//                .replace("<div id=\"main_content\" class=\"clearfix\">", "<div id=\"main_content\" class=\"clearfix\" style=\"width:95%;margin:0;padding:0\">")
//                .replace("<div id=\"left_col\">", "<div id=\"left_col\" style=\"width:95%;margin:0;padding:0\">")
//                .replace("<div class=\"post_wrap clearfix\" id=\"single\">", "<div class=\"post_wrap clearfix\" id=\"single\" style=\"width:95%;\">")
//                .replace("<div class=\"post\">", "<div class=\"post\" style=\"width:95%;\">")
//                .replace("<h3 class=\"title\"><span>", "<h3 class=\"title\" style=\"width:95%;\"><span style=\"width:95%;\">")
//                .replace("<div class=\"post_content\">", "<div class=\"post_content\" style=\"width:95%;\">")
//
//        ;


//        int i = content.indexOf("<div style=\"margin-top: 15px; font-style: italic\">\n" +
//                "<p><strong>原创文章，转载请注明：");
//        int j = content.indexOf("</div><!-- END .post_content -->");
//        if (i > 0 && j > 0) {
//            content = content.substring(0, i) + content.substring(j);
//        }
        FileUtils.writeStringToFile(html, content, "UTF-8");
    }
}

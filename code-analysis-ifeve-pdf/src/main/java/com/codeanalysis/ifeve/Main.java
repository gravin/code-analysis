package com.codeanalysis.ifeve;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

        replaceHtml(html);

        Document document = DocumentUtil.getDocument(html, new HashMap<>());
        List<String> idsToRemove = Arrays.asList("header", "bread_crumb", "comments_wrapper", "previous_next_post_single"
                , "container", "right_col", "footer", "wpstats", "google_esf"
        );
        for (String idToRemove : idsToRemove) {
            Element header = document.getElementById(idToRemove);
            if (header != null) header.remove();
        }

        List<String> classesToRemove = Arrays.asList("meta"
        );
        for (String classToRemove : classesToRemove) {
            Elements elementsByClass = document.getElementsByClass(classToRemove);
            if (elementsByClass != null) {
                for (Element ele :
                        elementsByClass) {
                    if (ele != null) {
                        ele.remove();
                    }
                }
            }
        }

        List<String> keywords = Arrays.asList("e-202029", "admin-ajax"
        );
        for (String keyword : keywords) {
            Elements scripts = document.getElementsByTag("script");
            if (scripts != null) {
                for (Element script :
                        scripts) {
                    if (script != null) {
                        String src = script.attr("src") + script.data();
                        if (StringUtils.contains(src, keyword)) {
                            script.remove();
                        }
                    }
                }
            }
        }

        String style1 = "width:100%;margin:0;padding:0;";
        String style2 = "width:100%;";
        String style3 = "width:95%;";

        document.getElementById("main_content").attr("style", style1);
        document.getElementById("left_col").attr("style", style1);

        document.getElementsByClass("post_wrap").attr("style", style3);
        document.getElementsByClass("post").attr("style", style2);
        document.getElementsByClass("title").attr("style", style2);
        document.getElementsByClass("post_content").attr("style", style2);
        document.getElementsByClass("title").get(0).getElementsByTag("span").attr("style", style2);

        Elements p = document.getElementsByTag("p");
        for (int i = 0; i < Math.min(p.size(), 5); i++) {
            Element e = p.get(i);
            if (e != null && StringUtils.startsWithAny(e.text(), "原文：", "译者：", "原文链接", "作者：")) {
                e.remove();
            }
        }
        DocumentUtil.write(html, document);


        System.out.println(html.getName() + " has been modified");
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


        int i = content.indexOf("<div style=\"margin-top: 15px; font-style: italic\">\n" +
                "<p><strong>原创文章，转载请注明：");
        int j = content.indexOf("</div><!-- END .post_content -->");
        if (i > 0 && j > 0) {
            content = content.substring(0, i) + content.substring(j);
        }
        FileUtils.writeStringToFile(html, content, "UTF-8");
    }
}

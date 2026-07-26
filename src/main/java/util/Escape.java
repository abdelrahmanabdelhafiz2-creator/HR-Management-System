package util;

/**
 * أداة بسيطة لعمل Escape للنصوص قبل عرضها في الـ JSP
 * عشان نمنع هجمات XSS (Cross-Site Scripting) لما نعرض بيانات جاية من المستخدم
 * أو من الداتا بيز (اسم، إيميل، تعليق، سبب المكافأة... إلخ).
 */
public final class Escape {

    private Escape() {
    }

    public static String html(Object value) {
        if (value == null) {
            return "";
        }

        String text = String.valueOf(value);
        StringBuilder result = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '&':
                    result.append("&amp;");
                    break;
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#x27;");
                    break;
                default:
                    result.append(c);
            }
        }

        return result.toString();
    }
}

package site.keyu.DFA;


/**
 * @Author:Keyu
 */
public class SensitiveWordFilter {
    private static InitSensitiveWord words = new InitSensitiveWord();

    private static final String DEFAULT_REPLACEMENT = "**";


    public String filter(String text){
        if (text == null){
            return text;
        }
        StringBuilder newtext = new StringBuilder();
        WordNode nowNode = words.getRoot();
        String temp = "";
        int begin = 0;
        int pos = 0;

        while(pos < text.length()){
            char c = text.charAt(pos);
            nowNode = nowNode.getNext().get(c);

            if (nowNode == null){
                if (temp != ""){
                    newtext.append(temp);
                }else {
                    newtext.append(c);
                    pos = begin + 1;
                }
                begin = pos;
                nowNode = words.getRoot();
                temp = "";
            }
            else {
                pos++;
                temp += c;
                //find sensitive word
                if (nowNode.getEnd()){
                    newtext.append(DEFAULT_REPLACEMENT);
                    begin = pos;
                    nowNode = words.getRoot();
                    temp = "";
                }

            }


        }

        newtext.append(text.substring(begin));

        return newtext.toString();
    }

}

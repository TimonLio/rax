package erp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import erp.dao.KeywordDao;
import erp.dao.UtilDao;
import erp.model.Util;

public class KeywordService {

    private final static Logger logger = Logger.getLogger(KeywordService.class);

    private KeywordDao keywordDao;
    private UtilDao utilDao;

    private Hashtable<String, String> tokenTable;

    public KeywordService() {
        tokenTable = new Hashtable<String, String>();
        tokenTable.put("rohto", "rohto");
        tokenTable.put("乐敦", "乐敦");
        tokenTable.put("肌研", "肌研");
        tokenTable.put("清爽", "清爽");
        tokenTable.put("清爽型", "清爽型");
        tokenTable.put("白润", "白润");
        tokenTable.put("化妆水", "化妆水");
        tokenTable.put("化妆", "化妆");
        tokenTable.put("浅咖啡", "浅咖啡");
        tokenTable.put("咖啡", "咖啡");
        tokenTable.put("抗痘", "抗痘");
        tokenTable.put("粉饼", "粉饼");
        tokenTable.put("蜜粉", "蜜粉");
        tokenTable.put("蜜粉饼", "蜜粉饼");
        tokenTable.put("象牙", "象牙");
        tokenTable.put("秋冬", "秋冬");
        tokenTable.put("弹力", "弹力");
        tokenTable.put("光泽", "光泽");
        tokenTable.put("保湿", "保湿");
        tokenTable.put("洗面奶", "洗面奶");
        tokenTable.put("洁面乳", "洁面乳");
        tokenTable.put("洗面", "洗面");
        tokenTable.put("洁面", "洁面");
        tokenTable.put("莎娜", "莎娜");
        tokenTable.put("豆乳", "豆乳");
        tokenTable.put("SANA", "SANA");
    }

    private boolean isToken(String q) {
        return (keywordDao != null) ? (keywordDao.read(q) != null) : tokenTable.containsKey(q);
    }

    public int saveTokens(String content) {
        logger.debug("saveTokens:" + content);
        String[] tokenList = content.split(" ");
        int maxTokenLength = 3;
        int tokenNum = 0;

		Util u = utilDao.read("max_token_length");
		if (u == null) {
			utilDao.create(new Util("max_token_length", String
					.valueOf(maxTokenLength)));
		} else {
			maxTokenLength = Integer.parseInt(u.getValue());
		}
        
        for (String token : tokenList) {
            switch (Character.getType(token.charAt(0))) {
            case Character.LOWERCASE_LETTER: // 2
            case Character.UPPERCASE_LETTER: // 1
            case Character.OTHER_LETTER: // 5
                if (token.length() > 1) {
                    // logger.info("Token to be saved:" + token);
                    if (keywordDao.create(token)) {
                        tokenNum++;
                        if (token.length() > maxTokenLength) {
                            u.setValue(String.valueOf(token.length()));
                            utilDao.update(u);
                        }
                    }
                }
                break;
            }
        }
        return tokenNum;
    }

    public List<String> parseToken(String content) {
        // 分词
        logger.debug("parseToken:" + content);
        List<String> tokenList = new ArrayList<String>();

        int maxTokenLength = 3;
        try {
        	maxTokenLength = Integer.parseInt(utilDao.read("max_token_length").getValue());
        } catch(Exception ex) {}

        char curChar;
        char[] ioBuf = content.replaceAll("\\p{Punct}", " ").toCharArray();
        int curType = 0;
        int lastType = 0;
        StringBuffer tokenBuf = new StringBuffer();
        for (int tail = ioBuf.length - 1; tail >= 0; tail--) {
            curChar = ioBuf[tail];
            switch (Character.getType(curChar)) {
            case Character.DECIMAL_DIGIT_NUMBER: // 9
                //curType = 2;
            	curType = 1; // Trust number is the same type as letters, e.g. Q10, 150ml
                break;
            case Character.LOWERCASE_LETTER: // 2
            case Character.UPPERCASE_LETTER: // 1
                curType = 1;
                break;
            case Character.OTHER_LETTER: // 5
                curType = 0;
                break;
            default:
                curType = 3;
                break;
            }
            if (tail == ioBuf.length - 1) {
                lastType = curType;
            }

            if (curType != lastType && tokenBuf.length() > 0) {
                tokenList.add(tokenBuf.reverse().toString());
                tokenBuf.delete(0, tokenBuf.length());
            }
            if (curType != 3) {
                tokenBuf.append(curChar);
            }
            lastType = curType;
        }
        if (tokenBuf.length() > 0) {
            tokenList.add(tokenBuf.reverse().toString());
        }

        String subToken;
        List<String> finalTokenList = new ArrayList<String>();
        for (String token : tokenList) {
            if (Character.getType(token.charAt(0)) == Character.OTHER_LETTER) {
                ioBuf = token.toCharArray();
                int inTokenPos = ioBuf.length;
                for (int tail = ioBuf.length - 1; tail >= 0; tail--) {
                    boolean found = false;
                    for (int len = Math.min(maxTokenLength, tail + 1); len > 1; len--) {
                        int tokenBeginPos = tail - len + 1;
                        subToken = new String(ioBuf, tokenBeginPos, len);
                        if (isToken(subToken)) {
                            // logger.info("subToken:" + subToken);
                            found = true;
                            if (tokenBeginPos < inTokenPos) {
                                inTokenPos = tokenBeginPos;
                            }
                            finalTokenList.add(subToken);
                        }
                    }
                    if (found == false && tail < inTokenPos) {
                        finalTokenList.add(String.valueOf(token.charAt(tail)));
                    }
                }
            } else {
                finalTokenList.add(token);
            }
        }
        Collections.reverse(finalTokenList);

        for (String token : finalTokenList) logger.info("finalToken:" + token);

        return finalTokenList;
    }

    public int getCount() {
        return keywordDao.count(0);
    }

    public static void main(String[] args) throws Exception {
        KeywordService service = new KeywordService();
        //service.parseToken("乐敦Rohto肌研-白润化妆水170ml超清爽型 浅咖啡-玻尿酸 抗痘蜜粉饼-象牙白绿盒");
        service.parseToken("秋冬新SANA/莎娜 豆乳Q10弹力光泽 保湿洗面奶 洁面乳150g");
        //service.saveTokens("乐敦 Rohto 肌研 白润 化妆水 170 ml 清爽 型");
    }

    public void setKeywordDao(KeywordDao keywordDao) {
        this.keywordDao = keywordDao;
    }

    public void setUtilDao(UtilDao utilDao) {
        this.utilDao = utilDao;
    }

}

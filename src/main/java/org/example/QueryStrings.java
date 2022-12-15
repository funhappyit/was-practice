package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    private List<QueryString> queryStrings = new ArrayList<>();

    public QueryStrings(String queryStringLines) {
        String[] queryStringTokens = queryStringLines.split("&");
        Arrays.stream(queryStringTokens)
                .forEach(queryString->{
                    String[] values = queryString.split("=");
                    if(values.length != 2){
                        try {
                            throw new IllegalAccessException("잘못된 QueryString 포멧을 가진 문자열입니다");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }else{
                        queryStrings.add(new QueryString(values[0],values[1]));
                    }

                });
    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }
}

/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Sep 1st 2014
 * v1.3
 */

package com.hryun.imdb;

/**
 * Created by vegidio on 9/1/14.
 */
public enum SearchType
{
    ALL("all"), TITLE("tt"), TV_EPISODE("ep"), NAME("nm"), COMPANY("co"), KEYWORD("kw"), CHARACTER("ch"), QUOTE("ch");

    private final String value;

    SearchType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}

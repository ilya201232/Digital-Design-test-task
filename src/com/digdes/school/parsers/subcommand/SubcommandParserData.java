package com.digdes.school.parsers.subcommand;

public class SubcommandParserData {
    private final String subcommandName;
    private final int index;
    private String dataString;

    public SubcommandParserData(String subcommandName, int index) {
        this.subcommandName = subcommandName;
        this.index = index;
    }

    public String getSubcommandName() {
        return subcommandName;
    }

    public int getIndex() {
        return index;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}

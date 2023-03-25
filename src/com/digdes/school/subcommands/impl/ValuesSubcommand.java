package com.digdes.school.subcommands.impl;

import com.digdes.school.constant.SubcommandName;
import com.digdes.school.exception.parser.subcommand.value.IllegalValueTypeException;
import com.digdes.school.exception.parser.subcommand.value.NoCollectionReferenceInValueException;
import com.digdes.school.exception.parser.subcommand.value.NullValueInValuesException;
import com.digdes.school.subcommands.Subcommand;

import java.util.Map;

public class ValuesSubcommand implements Subcommand {
    private final Map<String, Object> data;

    public ValuesSubcommand(Map<String, Object> data) {
        this.data = data;
    }
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public void performChecks(Map<String, String> fieldsTypes) {
        for (String field : data.keySet()) {
            if (!fieldsTypes.containsKey(field)) {
                throw new NoCollectionReferenceInValueException("'" + field + "'", data.get(field).toString());
            } else {
                if (data.get(field) != null && !data.get(field).getClass().getName().equals(fieldsTypes.get(field))) {
                    throw new IllegalValueTypeException(field, data.get(field).toString(), fieldsTypes.get(field), data.get(field).getClass().getName());
                }
            }
        }
    }

    @Override
    public String getSubcommandName() {
        return SubcommandName.VALUES;
    }
}

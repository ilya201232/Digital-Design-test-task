package com.digdes.school.parsers.subcommand.impl;

import com.digdes.school.constant.SubcommandName;
import com.digdes.school.exception.parser.subcommand.value.NoCollectionReferenceInValueException;
import com.digdes.school.exception.parser.subcommand.value.WrongValueOperationFormatException;
import com.digdes.school.parsers.subcommand.SubcommandParser;
import com.digdes.school.subcommands.Subcommand;
import com.digdes.school.subcommands.impl.ValuesSubcommand;
import com.digdes.school.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class ValuesSubcommandParser implements SubcommandParser {

    @Override
    public Subcommand parse(String data) throws
            WrongValueOperationFormatException, NoCollectionReferenceInValueException

    {
        Map<String, Object> valuesToProcess = new HashMap<>();

        data = data.trim();

        String[] operations = data.split(",");

        for (String operation : operations) {
            operation = operation.trim();

            if (!operation.contains("="))
                throw new WrongValueOperationFormatException(operation);

            String[] values = operation.split("=");
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }

            if (!values[0].contains("'")) {
                throw new NoCollectionReferenceInValueException(operation);
            } else {
                valuesToProcess.put(
                        values[0].substring(1, values[0].length() - 1),
                        Utils.parseValue(values[1])
                );
            }
        }

        return new ValuesSubcommand(valuesToProcess);
    }

    @Override
    public String getSubcommandName() {
        return SubcommandName.VALUES;
    }


}

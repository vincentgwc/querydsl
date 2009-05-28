/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.sql.oracle;

import java.math.BigInteger;

import com.mysema.query.sql.SQLPatterns;
import com.mysema.query.types.operation.Ops;

/**
 * OracleDialect is an Oracle specific extension of SqlOps
 * 
 * @author tiwe
 * @version $Id$
 */
public class OracleDialect extends SQLPatterns {
    {
        // type mappings
        addClass2TypeMappings("number(3,0)", Byte.class);
        addClass2TypeMappings("number(1,0)", Boolean.class);
        addClass2TypeMappings("number(19,0)", BigInteger.class, Long.class);
        addClass2TypeMappings("number(5,0)", Short.class);
        addClass2TypeMappings("number(10,0)", Integer.class);
        addClass2TypeMappings("double precision", Double.class);
        addClass2TypeMappings("varchar(4000 char)", String.class);

        // operator mappings
        add(Ops.MathOps.CEIL, "ceil(%s)");
        add(Ops.MathOps.RANDOM, "dbms_random.value");
        add(Ops.MathOps.LOG, "ln(%s)");
        add(Ops.MathOps.LOG10, "log(10,%s)");

        add(Ops.CONCAT, "%s || %s");
        add(Ops.StringOps.SPACE, "lpad('',%s,' ')");

        add(Ops.DateTimeOps.YEAR, "extract(year from %s)");
        add(Ops.DateTimeOps.MONTH, "extract(month from %s)");
        add(Ops.DateTimeOps.WEEK, "to_number(to_char(%s,'WW'))");
        add(Ops.DateTimeOps.DAY, "extract(day from %s)");

        add(Ops.DateTimeOps.HOUR, "to_number(to_char(%s,'HH24'))");
        add(Ops.DateTimeOps.MINUTE, "to_number(to_char(%s,'MI'))");
        add(Ops.DateTimeOps.SECOND, "to_number(to_char(%s,'SS'))");

        add(Ops.DateTimeOps.DAY_OF_MONTH, "to_number(to_char(%s,'DD'))");
        add(Ops.DateTimeOps.DAY_OF_WEEK, "to_number(to_char(%s,'D'))");
        add(Ops.DateTimeOps.DAY_OF_YEAR, "to_number(to_char(%s,'DDD'))");

        limitAndOffsetSymbols(false);
        limitTemplate("rownum < %s");
        offsetTemplate("rownum > %s");
        limitOffsetTemplate("rownum between %1$s and %3$s");
    }
}
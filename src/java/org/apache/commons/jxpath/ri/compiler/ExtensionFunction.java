/*
 * Copyright 1999-2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.jxpath.ri.compiler;

import java.util.Arrays;

import org.apache.commons.jxpath.Function;
import org.apache.commons.jxpath.JXPathException;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.QName;

/**
 * Represents  an element of the parse tree representing an extension function
 * call.
 *
 * @author Dmitri Plotnikov
 * @version $Revision$ $Date$
 */
public class ExtensionFunction extends Operation {

    private QName functionName;

    public ExtensionFunction(QName functionName, Expression args[]) {
        super(args);
        this.functionName = functionName;
    }

    public QName getFunctionName() {
        return functionName;
    }

    /**
     * An extension function gets the current context, therefore it MAY be
     * context dependent.
     */
    public boolean computeContextDependent() {
        return true;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(functionName);
        buffer.append('(');
        Expression args[] = getArguments();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }
                buffer.append(args[i]);
            }
        }
        buffer.append(')');
        return buffer.toString();
    }
    
    public Object compute(EvalContext context) {
        return computeValue(context);
    }

    public Object computeValue(EvalContext context) {
        Object[] parameters = null;
        if (args != null) {
            parameters = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                parameters[i] = convert(args[i].compute(context));
            }
        }

        Function function =
            context.getRootContext().getFunction(functionName, parameters);
        if (function == null) {
            throw new JXPathException(
                "No such function: "
                    + functionName
                    + Arrays.asList(parameters));
        }

        return function.invoke(context, parameters);
    }
    
    private Object convert(Object object) {
        if (object instanceof EvalContext) {
            return ((EvalContext) object).getValue();
        }
        return object;
    }  
}
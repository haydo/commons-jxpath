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

import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.EvalContext;

/**
 * An element of the compile tree holding a variable reference.
 *
 * @author Dmitri Plotnikov
 * @version $Revision$ $Date$
 */
public class VariableReference extends Expression {

    private QName varName;

    public VariableReference(QName varName) {
        this.varName = varName;
    }

    public QName getVariableName() {
        return varName;
    }

    public String toString() {
        return "$" + varName;
    }

    public boolean isContextDependent() {
        return false;
    }

    public boolean computeContextDependent() {
        return false;
    }

    public Object compute(EvalContext context) {
        return computeValue(context);
    }

    /**
     * Returns the value of the variable.
     */
    public Object computeValue(EvalContext context) {
        return context.getRootContext().getVariableContext(varName);
    }
}
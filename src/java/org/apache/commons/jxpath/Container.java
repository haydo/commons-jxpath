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
package org.apache.commons.jxpath;

/**
 * A Container is an object implementing an indirection
 * mechanism transparent to JXPath.  For example, if property
 * "foo" of the context node has a Container as its value,
 * the XPath "foo" will produce the contents of that Container,
 * rather than the container itself.
 *
 * @author Dmitri Plotnikov
 * @version $Revision$ $Date$
 */
public interface Container {

    /**
     * Returns the contained value.
     */
    Object getValue();

    /**
     * Modifies the value contained by this container.  May throw
     * UnsupportedOperationException.
     */
    void setValue(Object value);
}
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
package org.apache.commons.jxpath.ri.model.beans;

import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
 * An iterator of attributes of a JavaBean. Returns bean properties as
 * well as the "xml:lang" attribute.
 *
 * @author Dmitri Plotnikov
 * @version $Revision$ $Date$
 */
public class BeanAttributeIterator extends PropertyIterator {
    private NodePointer parent;
    private int position = 0;
    private boolean includeXmlLang;

    public BeanAttributeIterator(PropertyOwnerPointer parent, QName name) {
        super(
            parent,
            (name.getPrefix() == null
                && (name.getName() == null || name.getName().equals("*")))
                ? null
                : name.toString(),
            false,
            null);
        this.parent = parent;
        includeXmlLang =
            (name.getPrefix() != null && name.getPrefix().equals("xml"))
                && (name.getName().equals("lang") 
                || name.getName().equals("*"));
    }

    public NodePointer getNodePointer() {
        if (includeXmlLang && position == 1) {
            return new LangAttributePointer(parent);
        }
        else {
            return super.getNodePointer();
        }
    }

    public int getPosition() {
        return position;
    }

    public boolean setPosition(int position) {
        this.position = position;
        if (includeXmlLang) {
            if (position == 1) {
                return true;
            }
            else {
                return super.setPosition(position - 1);
            }
        }
        else {
            this.position = position;
            return super.setPosition(position);
        }
    }
}
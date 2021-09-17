/*

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.gvt;

import java.awt.Shape;

/**
 * Interface describing object that can be selected or have selections
 * made on it.
 *
 * @author <a href="mailto:bill.haneman@ireland.sun.com">Bill Haneman</a>
 * @version $Id: Selectable.java 1733416 2016-03-03 07:07:13Z gadams $
 */
public interface Selectable {

    /**
     * Initializes the current selection to begin with the character at (x, y).
     * @return true if action resulted in change of selection.
     */
    boolean selectAt(double x, double y);

    /**
     * Extends the current selection to the character at (x, y)..
     * @return true if action resulted in change of selection.
     */
    boolean selectTo(double x, double y);

    /**
     * Selects the entire contents of the GraphicsNode at (x, y).
     * @return true if action resulted in change of selection.
     */
    boolean selectAll(double x, double y);

    /**
     * Get the current text selection.
     * @return an object containing the selected content.
     */
    Object getSelection();

    /**
     * Return a shape in user coords which encloses the current selection.
     */
    Shape getHighlightShape();
}
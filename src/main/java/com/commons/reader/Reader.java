package com.commons.reader;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author caydogdu This is a interface for getting file input
 */
public interface Reader {

    List<List<String>> getFileInput() throws IOException;

}

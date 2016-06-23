package CustomReader;

import java.io.FilterReader;
import java.io.Reader;

public class ImportantWordsReader extends FilterReader {

	public ImportantWordsReader(Reader in) {
		super(in);
	}

}

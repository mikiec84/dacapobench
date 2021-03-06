package org.dacapo.analysis.util.events;

import org.dacapo.instrument.LogTags;
import org.dacapo.util.CSVInputStream;
import org.dacapo.util.CSVOutputStream;
import org.dacapo.util.CSVInputStream.NoFieldAvailable;
import org.dacapo.util.CSVInputStream.ParseError;

public class EventThreadStart extends Event implements EventHasThread {

	public  static final String TAG = LogTags.LOG_PREFIX_THREAD_START;
	
	private long threadTag;
	private long threadClassTag;
	private String threadClassName;
	private String threadName;
	
	public EventThreadStart(long time, long threadTag, long threadClassTag, String threadClassName, String threadName) {
		super(time);
		this.threadTag = threadTag;
		this.threadClassTag = threadClassTag;
		this.threadClassName = threadClassName;
		this.threadName = threadName;
	}
	
	public String getLogPrefix() { return TAG; }
	
	public long getThreadTag() { return threadTag; }
	public void setThreadTag(long threadTag) { this.threadTag = threadTag; }
	
	public long getThreadClassTag() { return threadClassTag; }
	public void setThreadClassTag(long threadClassTag) { this.threadClassTag = threadClassTag; }
	
	public String getThreadClass() { return threadClassName; }
	public void   setThreadClass(String threadClassName) { this.threadClassName = threadClassName; }
	
	public String getThreadName() { return threadName; }
	public void   setThreadName(String threadName) { this.threadName = threadName; }
	
	public String toString() {
		return super.toString()+":"+getThreadTag()+":"+getThreadClassTag()+":"+getThreadClass()+":"+getThreadName();
	}
	
	protected void writeEvent(CSVOutputStream os) {
		os.write(""+getTime());
		
		EventThread.write(os, this);
	}
	
	EventThreadStart(CSVInputStream is) throws NoFieldAvailable, ParseError, EventParseException {
		super(is);

		EventThread.read(is, this);

		if (is.numberOfFieldsLeft()!=0 && this instanceof EventThreadStart) 
			throw new EventParseException("additional fields",null);
	}
}

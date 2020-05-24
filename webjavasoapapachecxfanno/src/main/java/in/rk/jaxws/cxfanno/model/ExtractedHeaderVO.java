package in.rk.jaxws.cxfanno.model;

import org.w3c.dom.Document;

public class ExtractedHeaderVO {
	
	private String headerXMLString;
	private Document headerDocument;
	private String requestId;

	
	public String getHeaderXMLString() {
		return headerXMLString;
	}

	public void setHeaderXMLString(String headerXML) {
		this.headerXMLString = headerXML;
	}

	public Document getHeaderDocument() {
		return headerDocument;
	}

	public void setHeaderDocument(Document headerDocument) {
		this.headerDocument = headerDocument;
	}

	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String a) {
		this.requestId = a;
	}

	@Override
	public String toString() {
		return "ExtractedHeaderVO [headerXMLString=" + headerXMLString + ", requestId=" + requestId + "]";
	}
	
	
}

package in.rk.jaxws.cxfanno.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestData {

	private String soapString;
	private Map<String, List<String>> protocolHeaders;
	private ExtractedHeaderVO extractedHeaderVO;
	
	public String getSoapString() {
		return soapString;
	}
	public void setSoapString(String soapString) {
		this.soapString = soapString;
	}
	
	public Map<String, List<String>> getProtocolHeaders() {
		if ( null==protocolHeaders) {
			protocolHeaders=new HashMap<String, List<String>>();
		}
		return protocolHeaders;
	}
	public void setProtocolHeaders(Map<String, List<String>> protocolHeaders) {
		this.protocolHeaders = protocolHeaders;
	}
	public ExtractedHeaderVO getExtractedHeaderVO() {
		if ( null==extractedHeaderVO) {
			extractedHeaderVO= new ExtractedHeaderVO();
		}
		return extractedHeaderVO;
	}
	public void setExtractedHeaderVO(ExtractedHeaderVO extractedHeaderVO) {
		this.extractedHeaderVO = extractedHeaderVO;
	}
	@Override
	public String toString() {
		return "RequestData [soapString=" + soapString + ", protocolHeaders=" + protocolHeaders + ", extractedHeaderVO="
				+ extractedHeaderVO + "]";
	}
	
	
	
}

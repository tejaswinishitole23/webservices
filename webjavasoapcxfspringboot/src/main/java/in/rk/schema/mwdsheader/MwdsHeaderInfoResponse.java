
package in.rk.schema.mwdsheader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="actualUser" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="impersonateUser" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="apidescription" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestId",
    "actualUser",
    "impersonateUser",
    "apidescription"
})
@XmlRootElement(name = "mwdsHeaderInfoResponse")
public class MwdsHeaderInfoResponse {

    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected String actualUser;
    @XmlElement(required = true)
    protected String impersonateUser;
    @XmlElement(required = true)
    protected String apidescription;

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the actualUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualUser() {
        return actualUser;
    }

    /**
     * Sets the value of the actualUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualUser(String value) {
        this.actualUser = value;
    }

    /**
     * Gets the value of the impersonateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpersonateUser() {
        return impersonateUser;
    }

    /**
     * Sets the value of the impersonateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpersonateUser(String value) {
        this.impersonateUser = value;
    }

    /**
     * Gets the value of the apidescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApidescription() {
        return apidescription;
    }

    /**
     * Sets the value of the apidescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApidescription(String value) {
        this.apidescription = value;
    }

}

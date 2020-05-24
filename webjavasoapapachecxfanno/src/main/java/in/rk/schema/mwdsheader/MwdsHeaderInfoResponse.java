
package in.rk.schema.mwdsheader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="actualUser" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="impersonateUser" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="apidescription" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
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

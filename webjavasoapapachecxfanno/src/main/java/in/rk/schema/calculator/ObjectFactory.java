
package in.rk.schema.calculator;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the in.rk.schema.calculator package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: in.rk.schema.calculator
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateDataRequest }
     * 
     */
    public CalculateDataRequest createCalculateDataRequest() {
        return new CalculateDataRequest();
    }

    /**
     * Create an instance of {@link UserType }
     * 
     */
    public UserType createUserType() {
        return new UserType();
    }

    /**
     * Create an instance of {@link CalculateDataResponse }
     * 
     */
    public CalculateDataResponse createCalculateDataResponse() {
        return new CalculateDataResponse();
    }

}

package com.rightkarma.svc.rest.model;

public class SalutationResponse {

	private String salutationResponse;

	public SalutationResponse() {

	}

	public String getSalutationResponse() {
		return salutationResponse;
	}

	
	public void setSalutationResponse(String salutationResponse) {
		this.salutationResponse = salutationResponse;
	}

	@Override
	public String toString() {
		return "SalutationResponse [salutationResponse=" + salutationResponse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salutationResponse == null) ? 0 : salutationResponse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalutationResponse other = (SalutationResponse) obj;
		if (salutationResponse == null) {
			if (other.salutationResponse != null)
				return false;
		} else if (!salutationResponse.equals(other.salutationResponse))
			return false;
		return true;
	}

}

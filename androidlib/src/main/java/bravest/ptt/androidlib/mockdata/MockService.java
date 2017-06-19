package bravest.ptt.androidlib.mockdata;

import bravest.ptt.androidlib.net.DataResponse;

public abstract class MockService {
	public abstract String getJsonData();
	
	public DataResponse getSuccessResponse() {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setError(false);
		dataResponse.setErrorType(0);
		dataResponse.setErrorMessage("");

		return dataResponse;
	}

	public DataResponse getFailResponse(int errorType, String errorMessage) {
		DataResponse dataResponse = new DataResponse();
		dataResponse.setError(true);
		dataResponse.setErrorType(errorType);
		dataResponse.setErrorMessage(errorMessage);
		
		return dataResponse;
	}
}

	public static double factoria(double inputValue) {
		// base-case
		if (inputValue <= 1) 
			return inputValue;
		//recursive case
		return inputValue*factoria(inputValue - 1);
	}
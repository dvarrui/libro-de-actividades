public static void main(String[] args){
		//dot 1
		double d;
		//dot 2
		if(args.length>0) 
			d = Double.parseDouble(args[0]);
		else
			d=5;
			
		System.out.printf("Factorial [%.1f] of [%.1f]",factoria(d),d);
		
		}

// output:
		// Factorial [120.0] of [5.0]
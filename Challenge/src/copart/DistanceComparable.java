package copart;

import java.util.Comparator;

public class DistanceComparable implements Comparator<CopartFacility> {

	@Override
	public int compare(CopartFacility o1, CopartFacility o2) {
		return (int) (o1.getCustomerDistance() - o2.getCustomerDistance());
	}
	
}

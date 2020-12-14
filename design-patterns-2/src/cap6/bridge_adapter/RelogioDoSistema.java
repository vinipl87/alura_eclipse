package cap6.bridge_adapter;

import java.util.Calendar;

public class RelogioDoSistema implements Relogio{
	//DP: Adapter
	public Calendar hoje(){
		return Calendar.getInstance();
	}
}

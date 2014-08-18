package scripts.actions.Cut;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.graphics.UserSelections;
import scripts.sbf.util.MFUtil;

public class SelectMakeX extends Action {

	@Override
	public void execute() {
		RSInterface cutProduct = skillManager.getChildInterfaceFor(Interfaces
				.get(skillManager.getMasterIndex()), UserSelections
				.getInstance().get("Product"));
		if (cutProduct != null)
			if (cutProduct.click("Make X"))
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						General.sleep(50, 70);
						return MFUtil.enterAmountIsOpen();
					}

				}, General.random(3000, 4000));
	}

	@Override
	public boolean isValid() {
		return Globals.SELECT_MAKE_X.getStatus();
	}

}

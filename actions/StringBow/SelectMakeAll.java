package scripts.actions.StringBow;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSInterface;

import scripts.Globals;
import scripts.sbf.action.Action;

public class SelectMakeAll extends Action {

	@Override
	public void execute() {
		RSInterface stringingBow = skillManager
				.getChildInterfaceFor(Interfaces.get(309),
						selections.get("Product").split(" [(]")[0]);

		if (stringingBow != null)
			if (stringingBow.click("Make All"))
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						General.sleep(100, 200);
						return Player.getAnimation() > -1;
					}

				}, General.random(3000, 4000));
	}

	@Override
	public boolean isValid() {
		return Globals.SELECT_MAKE_ALL.getStatus();
	}

}

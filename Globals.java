package scripts;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;

import scripts.sbf.graphics.UserSelections;
import scripts.sbf.skill.SkillGlobals;
import scripts.sbf.skill.SkillManager;
import scripts.sbf.util.MFUtil;

public enum Globals {
	DEPOSIT {

		@Override
		public boolean getStatus() {
			return Banking.isBankScreenOpen()
					&& BANKING_NEEDS_DEPOSIT.getStatus();
		}

	},
	CUT {

		@Override
		public boolean getStatus() {
			return Inventory.find(skillManager.getInventoryResources()).length > 0;
		}

	},
	MAKE {

		@Override
		public boolean getStatus() {
			return Inventory.find(itemToUse).length > 0
					&& Inventory.find("Feather").length > 0
					|| (Inventory.find(itemToUse).length > 0
							&& itemToUse.equalsIgnoreCase("Headless arrow") && Inventory
							.find(product.concat("tips")).length > 0);
		}

	},
	OPEN_BANK {

		@Override
		public boolean getStatus() {
			return !Banking.isBankScreenOpen()
					&& !SkillGlobals.SKILLING.getUpdatedStatus()
					&& (BANKING_NEEDS_WITHDRAW.getStatus() || BANKING_NEEDS_DEPOSIT
							.getStatus());
		}

	},
	STRING_BOW {

		@Override
		public boolean getStatus() {
			return Inventory.find(product).length > 0
					&& Inventory.find("Bow string").length > 0;

		}

	},
	WITHDRAW {

		@Override
		public boolean getStatus() {
			return Banking.isBankScreenOpen() && !Inventory.isFull()
					&& BANKING_NEEDS_WITHDRAW.getStatus();
		}

	},
	BANKING_NEEDS_WITHDRAW {

		@Override
		public boolean getStatus() {

			return (userSelections.get("Action").equals("Cut")
					&& Inventory.getCount("Knife") < 1 && Inventory
					.getCount(product) < 1)
					|| (userSelections.get("Action").equals("String")
							&& Inventory.getCount("Bow string") < 1 && Inventory
							.getCount(product) < 1)
					|| Inventory.find(skillManager.getInventoryResources()).length < skillManager
							.getInventoryResources().length;
		}

	},
	BANKING_NEEDS_DEPOSIT {

		@Override
		public boolean getStatus() {
			return (Inventory.isFull() && ((userSelections.get("Action")
					.equals("Cut") && Inventory.getCount("Knife") < 1 || Inventory
					.getCount(product) < 1)
					|| (userSelections.get("Action").equals("String")
							&& Inventory.getCount("Bow string") < 1 || Inventory
							.getCount(product) < 1) || (Inventory
					.find(skillManager.getInventoryResources()).length < skillManager
					.getInventoryResources().length)))
					|| !MFUtil.inPossessionOfOnly(skillManager
							.getInventoryResources());
		}

	},
	ENTER_AMOUNT_TO_CUT {

		@Override
		public boolean getStatus() {

			return HAS_RESOURCES.getStatus() && MFUtil.enterAmountIsOpen();
		}

	},
	SELECT_MAKE_X {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus()
					&& Interfaces.get(skillManager.getMasterIndex()) != null;
		}

	},
	USE_KNIFE {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus();
		}

	},
	MAKE_UNF {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus()
					&& UserSelections.getInstance().get("ItemToUse")
							.contains("(unf)");
		}

	},
	CLICK_TEN_SETS {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus() && !MAKE_UNF.getStatus()
					&& Interfaces.get(skillManager.getMasterIndex()) != null;
		}

	},
	USE_ITEM_ONE_WITH_TWO {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus() && !MAKE_UNF.getStatus();
		}

	},
	SELECT_MAKE_ALL {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus() && Interfaces.get(skillManager.getMasterIndex()) != null;
		}

	},
	USE_BS {

		@Override
		public boolean getStatus() {
			return HAS_RESOURCES.getStatus() && Interfaces.get(skillManager.getMasterIndex()) == null;
		};

	},
	HAS_RESOURCES {

		@Override
		public boolean getStatus() {
			return Inventory.find(skillManager.getInventoryResources()).length > 1;
		}

	};
	public abstract boolean getStatus();

	private static SkillManager skillManager = SkillManager.getInstance();
	private static UserSelections userSelections = UserSelections.getInstance();
	private static String itemToUse = userSelections.get("ItemToUse");
	private static String product = userSelections.get("Product");

}

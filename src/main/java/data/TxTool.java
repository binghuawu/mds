package data;

import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public final class TxTool {

	public static boolean isTransactionActive() {
		try {
			TransactionAspectSupport.currentTransactionStatus();
			return true;
		} catch (NoTransactionException notx) {
			notx.printStackTrace();
			return false;
		}
	}

	public static boolean isNewTransaction() {
		try {
			return TransactionAspectSupport.currentTransactionStatus()
					.isNewTransaction();
		} catch (NoTransactionException notx) {
			notx.printStackTrace();
			return false;
		}
	}
}

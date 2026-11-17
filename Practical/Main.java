public class Main {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount(
                new AccountNumber("ACC101"),
                new Money("10000"),
                new OverdraftLimit(new Money("2000")));

        BankAccount accountB = new BankAccount(
                new AccountNumber("ACC102"),
                new Money("5000"),
                new OverdraftLimit(new Money("1000")));

        accountA.deposit(new Money("500"));
        accountA.withdraw(new Money("1000"));
        FundTransfer.transfer(accountA, accountB, new Money("1500"));

        System.out.println("CASE STUDY 3 - BANKING SYSTEM");
        System.out.println("Account A balance: " + accountA.getBalance());
        System.out.println("Account B balance: " + accountB.getBalance());

        accountA.freeze();
        try {
            accountA.withdraw(new Money("100"));
        } catch (IllegalStateException ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }

        System.out.println("\nAccount A Ledger:");
        for (LedgerEntry entry : accountA.getLedger()) {
            System.out.println(entry);
        }

        System.out.println("\nAccount B Ledger:");
        for (LedgerEntry entry : accountB.getLedger()) {
            System.out.println(entry);
        }
    }
}

public interface Layer2Listener {
    //void bitsReceived(BitHandler handler, String bits);
    void frameRecieved(L2Handler h, L2Frame f);
}
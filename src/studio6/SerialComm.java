package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte x) throws SerialPortException {
		if(debug) {
			port.writeByte(x);
			System.out.println(x);
		}
	}
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		int x = port.getInputBufferBytesCount();
		if(x >= 1) {
			return true;
		} else {
			return false;
		}
	}
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException {
		if (!debug) {
			byte x = port.readBytes(1)[0];
			System.out.println(String.format("%02x", x));
			return x;
		} else {
			return 0;
		}
	}
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm a1 = new SerialComm("COM5");
		while(true){
			if(a1.available()){	
			byte storedData = a1.readByte();
			System.out.println((char)storedData);
			}
		}
		
	}
}

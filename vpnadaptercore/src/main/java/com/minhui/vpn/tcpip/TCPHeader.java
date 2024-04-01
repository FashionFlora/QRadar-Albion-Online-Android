package com.minhui.vpn.tcpip;

import com.minhui.vpn.utils.CommonMethods;

/**
 * Created by zengzheying on 15/12/28.
 */
public class TCPHeader
{
	public static final int FIN = 1;
	public static final int SYN = 2;
	public static final int RST = 4;
	public static final int PSH = 8;
	public static final int ACK = 16;
	public static final int URG = 32;

	static final short offset_src_port = 0; // 16位源端口
	static final short offset_dest_port = 2; // 16位目的端口
	static final int offset_seq = 4; //32位序列号
	static final int offset_ack = 8; //32位确认号
	static final byte offset_lenres = 12; //4位首部长度 + 4位保留位
	static final byte offset_flag = 13; //2位保留字 + 6位标志位
	static final short offset_win = 14; //16位窗口大小
	static final short offset_crc = 16; //16位校验和

	public byte[] mData;
	public int mOffset;

	public TCPHeader(byte[] data, int offset)
	{
		mData = data;
		mOffset = offset;
	}

	public int getHeaderLength()
	{
		int lenres = mData[mOffset + offset_lenres] & 0xFF;
		return (lenres >> 4) * 4;
	}

	public short getSourcePort() {
		return CommonMethods.readShort(mData, mOffset + offset_src_port);
	}

	public void setSourcePort(short value)
	{
		CommonMethods.writeShort(mData, mOffset + offset_src_port, value);
	}

	public short getDestinationPort()
	{
		return CommonMethods.readShort(mData, mOffset + offset_dest_port);
	}

	public void setDestinationPort(short value)
	{
		CommonMethods.writeShort(mData, mOffset + offset_dest_port, value);
	}

	public byte getFlag() {
		return mData[mOffset + offset_flag];
	}

	public short getCrc() {
		return CommonMethods.readShort(mData, mOffset + offset_crc);
	}

	public void setCrc(short value) {
		CommonMethods.writeShort(mData, mOffset + offset_crc, value);
	}

	public int getSeqID() {
		return CommonMethods.readInt(mData, mOffset + offset_seq);
	}

	public int getAckID() {
		return CommonMethods.readInt(mData, mOffset + offset_ack);
	}

	@Override
	public String toString()
	{
		return String.format("%s%s%s%s%s%s %d->%d %s:%s",
				(getFlag() & SYN) == SYN ? "SYN" : "",
				(getFlag() & ACK) == ACK ? "ACK" : "",
				(getFlag() & PSH) == PSH ? "PSH" : "",
				(getFlag() & RST) == RST ? "RST" : "",
				(getFlag() & FIN) == FIN ? "FIN" : "",
				(getFlag() & URG) == URG ? "URG" : "",
				getSourcePort() & 0xFFFF,
				getDestinationPort() & 0xFFFF,
				getSeqID(),
				getAckID());
	}
}

package com.minhui.vpn.tcpip;

import com.minhui.vpn.utils.CommonMethods;

/**
 * Created by zengzheying on 15/12/28.
 */
public class IPHeader
{

	public static final byte TCP = 6;  //6: TCP协议号
	public static final byte UDP = 17; //17: UDP协议号
	public static final byte offset_proto = 9; //9：8位协议偏移
	public static final int offset_src_ip = 12; //12：源ip地址偏移
	public static final int offset_dest_ip = 16; //16：目标ip地址偏移
	static final byte offset_ver_ihl = 0; //0: 版本号（4bits） + 首部长度（4bits）
	static final byte offset_tos = 1; //1：服务类型偏移
	static final short offset_tlen = 2; //2：总长度偏移
	static final short offset_identification = 4; //4：16位标识符偏移
	static final short offset_flags_fo = 6; //6：标志（3bits）+ 片偏移（13bits）
	static final byte offset_ttl = 8; //8：生存时间偏移
	static final short offset_crc = 10; //10：首部校验和偏移

	public byte[] mData;
	public int mOffset;

	public IPHeader(byte[] data, int offset)
	{
		mData = data;
		mOffset = offset;
	}

	public int getDataLength() {
		return this.getTotalLength() - this.getHeaderLength();
	}

	public int getHeaderLength()
	{
		return (mData[mOffset + offset_ver_ihl] & 0x0F) * 4;
	}

	public int getTotalLength()
	{
		return CommonMethods.readShort(mData, mOffset + offset_tlen) & 0xFFFF;
	}

	public byte getProtocol() {
		return mData[mOffset + offset_proto];
	}

	public short getCrc() {
		return CommonMethods.readShort(mData, mOffset + offset_crc);
	}

	public void setCrc(short value) {
		CommonMethods.writeShort(mData, mOffset + offset_crc, value);
	}

	public int getSourceIP() {
		return CommonMethods.readInt(mData, mOffset + offset_src_ip);
	}

	public void setSourceIP(int value)
	{
		CommonMethods.writeInt(mData, mOffset + offset_src_ip, value);
	}

	public int getDestinationIP() {
		return CommonMethods.readInt(mData, mOffset + offset_dest_ip);
	}

	public void setDestinationIP(int value)
	{
		CommonMethods.writeInt(mData, mOffset + offset_dest_ip, value);
	}

	@Override
	public String toString()
	{
		return String.format("%s->%s Pro=%s, HLen=%d", CommonMethods.ipIntToString(getSourceIP()),
				CommonMethods.ipIntToString(getDestinationIP()), getProtocol(), getHeaderLength());
	}
}

package com.minhui.vpn.tcpip;

import com.minhui.vpn.utils.CommonMethods;

/**
 * Created by zengzheying on 15/12/28.
 */
public class UDPHeader
{
	static final short offset_src_port = 0; // 源端口
	static final short offset_dest_port = 2; //目的端口

	public byte[] mData;
	public int mOffset;

	public UDPHeader(byte[] data, int offset)
	{
		mData = data;
		mOffset = offset;
	}

	public short getSourcePort() {
		return CommonMethods.readShort(mData, mOffset + offset_src_port);
	}

	public short getDestinationPort()
	{
		return CommonMethods.readShort(mData, mOffset + offset_dest_port);
	}

	@Override
	public String toString()
	{
		return String.format("%d->%d", getSourcePort() & 0xFFFF, getDestinationPort() & 0xFFFF);
	}
}

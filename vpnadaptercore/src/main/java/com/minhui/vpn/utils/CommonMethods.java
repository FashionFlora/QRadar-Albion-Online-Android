package com.minhui.vpn.utils;


import android.util.Log;

import com.minhui.vpn.tcpip.IPHeader;
import com.minhui.vpn.tcpip.TCPHeader;
import com.minhui.vpn.tcpip.UDPHeader;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zengzheying
 */
public class CommonMethods
{
	public static String ipIntToString(int ip)
	{
		return String.format("%s.%s.%s.%s", (ip >> 24) & 0x00FF,
				(ip >> 16) & 0x00FF, (ip >> 8) & 0x00FF, (ip & 0x00FF));
	}

	public static int ipStringToInt(String ip)
	{
		String[] arrayStrings = ip.split("\\.");

		int r = (Integer.parseInt(arrayStrings[0]) << 24)
				| (Integer.parseInt(arrayStrings[1]) << 16)
				| (Integer.parseInt(arrayStrings[2]) << 8)
				| (Integer.parseInt(arrayStrings[3]));

		return r;
	}

	public static int readInt(byte[] data, int offset)
	{
		int r = ((data[offset] & 0xFF) << 24)
				| ((data[offset + 1] & 0xFF) << 16)
				| ((data[offset + 2] & 0xFF) << 8)
				| (data[offset + 3] & 0xFF);

		return r;
	}

	public static short readShort(byte[] data, int offset)
	{
		int r = ((data[offset] & 0xFF) << 8) | (data[offset + 1] & 0xFF);
		return (short) r;
	}

	public static void writeInt(byte[] data, int offset, int value)
	{
		data[offset] = (byte) (value >> 24);
		data[offset + 1] = (byte) (value >> 16);
		data[offset + 2] = (byte) (value >> 8);
		data[offset + 3] = (byte) value;
	}

	public static void writeShort(byte[] data, int offset, short value)
	{
		data[offset] = (byte) (value >> 8);
		data[offset + 1] = (byte) (value);
	}

	public static short checksum(long sum, byte[] buf, int offset, int len)
	{
		sum += getsum(buf, offset, len);

		while ((sum >> 16) > 0)
		{
			sum = (sum & 0xFFFF) + (sum >> 16);
		}

		return (short) ~sum;
	}

	public static long getsum(byte[] buf, int offset, int len)
	{
		long sum = 0;

		while (len > 1)
		{
			sum += readShort(buf, offset) & 0xFFFF;
			offset += 2;
			len -= 2;
		}

		if (len > 0)
		{
			sum += (buf[offset] & 0xFF) << 8;
		}

		return sum;
	}

	public static boolean ComputeIPChecksum(IPHeader ipHeader)
	{
		short oldCrc = ipHeader.getCrc();
		ipHeader.setCrc((short) 0);
		short newCrc = CommonMethods.checksum(0, ipHeader.mData, ipHeader.mOffset, ipHeader.getHeaderLength());
		ipHeader.setCrc(newCrc);
		return oldCrc == newCrc;
	}


	public static boolean ComputeTCPChecksum(IPHeader ipHeader, TCPHeader tcpHeader)
	{
		ComputeIPChecksum(ipHeader);

		int ipData_len = ipHeader.getDataLength();

		if (ipData_len < 0)
		{
			return false;
		}

		long sum = getsum(ipHeader.mData, ipHeader.mOffset + IPHeader.offset_src_ip, 8);
		sum += ipHeader.getProtocol() & 0xFF;
		sum += ipData_len;

		short oldCrc = tcpHeader.getCrc();
		tcpHeader.setCrc((short) 0);

		short newCrc = checksum(sum, tcpHeader.mData, tcpHeader.mOffset, ipData_len);

		tcpHeader.setCrc(newCrc);
		return oldCrc == newCrc;
	}
}

package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;
import java.util.HashMap;
import com.minhui.vpn.PhotonPackageParser.Utils;

public class NewAuctionEvent
{
    public NewAuctionEvent()
    {

    }

    public void AuctionGetOffersRequest(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        String category = Utils.getString(parameters.get(1));
        String subcategory = Utils.getString(parameters.get(2));
        int quality = Utils.getNumber(parameters.get(3));
        int tier = Utils.getNumber(parameters.get(5));
        int[] itemsID = Utils.getKnownArray(parameters.get(6));
        int enchantment = Utils.getNumber(parameters.get(8));

        Log.d("AuctionGetOffersRequest","id: " + id + " category:" + category + " subcategory:" + subcategory + " quality:" + quality + " tier:" + tier + " enchantment:" + enchantment + " itemsID:" + "");
    }

    public void AuctionGetOffersResponse(HashMap<Object, Object> parameters)
    {
        String line = Utils.getString(parameters.get(0));

        /*
         string line = JsonConvert.SerializeObject(parameters[0]);
         JArray offers = JArray.Parse(line);
         List<AuctionItems> offerList = new List<AuctionItems>();

         foreach (JValue value in offers)
         {
             JObject offer = JObject.Parse(value.ToString());
             long id = ((long)offer.GetValue("Id"));
             long unitPrice = ((long)offer.GetValue("UnitPriceSilver"));
             long totalPrice = ((long)offer.GetValue("TotalPriceSilver"));
             int amount = ((int)offer.GetValue("Amount"));
             short tier = ((short)offer.GetValue("Tier"));
             bool isFinished = ((bool)offer.GetValue("IsFinished"));
             string auctionType = offer.GetValue("IsFinished").ToString();
             bool hasBuyerFetched = ((bool)offer.GetValue("HasBuyerFetched"));
             bool hasSellerFetched = ((bool)offer.GetValue("HasSellerFetched"));
             string sellerCharacterId = offer.GetValue("SellerCharacterId").ToString();
             string sellerName = offer.GetValue("SellerName").ToString();
             string buyerCharacterId = offer.GetValue("BuyerCharacterId").ToString();
             string buyerName = offer.GetValue("BuyerName").ToString();
             string itemTypeId = offer.GetValue("ItemTypeId").ToString();
             string ItemGroupTypeId = offer.GetValue("ItemGroupTypeId").ToString();
             short enchantmentLevel = ((short)offer.GetValue("EnchantmentLevel"));
             short qualityLevel = ((short)offer.GetValue("QualityLevel"));
             string expires = offer.GetValue("Expires").ToString();
             string referenceId = offer.GetValue("ReferenceId").ToString();

             var auctionOffer = new AuctionItems(id, unitPrice, totalPrice, amount, tier, isFinished, auctionType, hasBuyerFetched, hasSellerFetched, sellerCharacterId, sellerName, buyerCharacterId, buyerName, itemTypeId, ItemGroupTypeId, enchantmentLevel, qualityLevel, expires, referenceId);
             offerList.Add(auctionOffer);
         }
         */

        Log.d("AuctionGetOffersResponse","id: " + line);
    }
}

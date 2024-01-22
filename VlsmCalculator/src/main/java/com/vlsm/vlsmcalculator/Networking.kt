package com.vlsm.vlsmcalculator

import com.vlsm.vlsmcalculator.model.Subnet


public class Networking {

    companion object {
        private val INSTANCE: Networking = Networking()
        public fun getInstance() = INSTANCE
    }

    public fun calculateVlsm(
        ipAddress: String,
        hostNumbers: HashMap<String, Int>,
    ): List<Subnet> {
        return VlsmCalculator.calculateVLSM(ipAddress, hostNumbers)
    }

}
package com.vlsm.vlsmcalculator

import com.vlsm.vlsmcalculator.model.Subnet


class Networking {

    companion object {
        private val INSTANCE: Networking = Networking()
        public fun getInstance() = INSTANCE
    }

    fun calculateVlsm(
        ipAddress: String,
        hostNumbers: HashMap<String, Int>,
    ): List<Subnet> {
        return VlsmCalculator.calculateVLSM(ipAddress, hostNumbers)
    }

}
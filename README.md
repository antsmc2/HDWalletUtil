## Overview

The repo aims to provide a set of utilities written in Java for managing
Hierarchical Deterministic Wallets (HD Wallets) using BIP39, BIP32 and BIP44 specifications. 
Currently only Bitcoin, Ethereum and Tron are supported. 

The hope is that this utility can serve as a starting point
for anyone wishing to build HD Wallets for any coin type.
There currently isn't many libraries that easily supports this accross coin types, hence this attempt in hopes that someone finds it useful.
Even though it is still work in progress, the code  mostly follows the TDD approach to ensure all functionality is tested.

## Features

- Generation of Mnemonic Phrases
- Deriving the private keys for a given coin type
- Deriving the public addresses for a given coin type

## Installation

For now, you can clone this repo into your project. 
A jar file and gradle/Maven setup information will most likely be provided in the near future.

## Usage

### Generating fresh mnemonic phrases
```
import java.util.List;
import com.antsmc2.common.CommonUtil;

List<String> words = CommonUtil.generateMnemonicsPhrase();
```

### Creating Wallets
*Note: The mnemonic phrase can be freshly generated or loaded from a storage location*

```
import java.util.List;
import com.antsmc2.common.CryptoWallet;
import com.antsmc2.wallet.BitcoinWallet;
import com.antsmc2.wallet.EthereumWallet;
import com.antsmc2.wallet.TronWallet;

CryptoWallet wallet = null;

// Create a Bitcoin wallet for test net
wallet = new BitcoinWallet(BitcoinWallet.NetworkType.TEST_NET, words);

// Create a Bitcoin wallet for main net
wallet = new BitcoinWallet(BitcoinWallet.NetworkType.MAIN_NET, words);

// Create an Ethereum wallet 
wallet = new EthereumWallet(words);

// Create a Tron wallet
wallet = new TronWallet(words);
```

All coin types share the same interface `CryptoWallet` interface.
The methods you'll need is expected to be defined on `CryptoWallet` interface.

### Deriving PrivateKeys and Public Addresses

After the wallet is defined, the addresses can be easily derived as follows.

```
// Derive the private key for BIP44 path String.format("m/44'/%s'/0'/0/2", coinType)
// E.g. This For Bitcoin mainnet (coinType = 2), this corresponds to path: m/44'/0'/0'/0/2
// E.g. This For ethereum (coinType = 60), this corresponds to path: m/44'/60'/0'/0/2
// E.g. This For Tron (coinType = 195), this corresponds to path: m/44'/195'/0'/0/2
String derivedPrivateKey = wallet.getNormalizedPrivateKey(2);

// Get the public address for receiving coins
// This example gets the address for account 11 for BIP44 path String.format("m/44'/%s'/0'/0/2", coinType)
String address = wallet.getNormalizedPublicAddress(11);
```


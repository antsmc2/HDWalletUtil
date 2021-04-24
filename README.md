##Overview
The repo aims to provide a java based set of utilities for managing crypto currency
Hierarchical Deterministic Wallets (HD Wallets) using BIP39, BIP32 and BIP44 specifications. 
Currently only Bitcoin, Ethereum and Tron are supported. 

The intention is to create a simple set of utilities in Java to serve as a starting point
for anyone wishing to build HD Wallets for any coin type.
I couldn't find any library that works accross coin types hence this attempt in hopes that someone finds it useful.
Even though is still work in progress, I try to follow a TDD approach to ensure all functionality is tested.

## Features

- Generation of Mnemonic Phrases used in creating HD Wallets
- Determining all private keys for a given coin type
- Determining the public address for a given coin type

## Installation

For now, you can clone this repo into your project.
I will likely be including a jar file and gradle/Maven setup information in the near future.

## Usage

### Generating fresh mnemonic words
```
import java.util.List;
import com.antsmc2.common.CommonUtil;

List<String> words = CommonUtil.generateMnemonicsPhrase();
```

### Creating Wallets
*Note: The mnemonic phrase can be a freshly generated or retrieved from a storage location*

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

### Deriving PrivateKeys and Public Addresses
*Note: The mnemonic phrase can be a freshly generated or retrieved from a storage location*

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


# Overview
This repository contains the online appendices coming with the manuscript "**Artefact-Centric Modelling and Implementation of Blockchain-Enabled Business Processes**", submitted to the [Business & Information Systems Engineering (BISE)](https://www.bise-journal.com/) journal.

# Supply Chain Case Study
Case study in which customers, manufacturers and carriers collaborate along 3 processes: ordering, production & delivery, and invoicing. The collaboration requires data sharing across processes and participants. Blockchain is proposed as a solution to support the collaboration despite the lack of trust among the participants. To model the required smart contracts, the B-MERODE language is used. The models can in turn be used to automatically generate
the smart contracts.

## Files
* *blinded-case-study.pdf*: Detailed presentation and description of the case study and proposed B-MERODE model.
* *model/model.mxp*: XML file describing the MERODE model proposed in the case study.
* *model/model.mxpx*: XML B-MERODE extension file for the model proposed in the case study.
* *generated/*: folder that contains the Hyperledger Fabric Chaincode generated from the .mxp and .mxpx files.
